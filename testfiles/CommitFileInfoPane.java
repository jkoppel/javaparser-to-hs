/*
 *  Gruntspud
 *
 *  Copyright (C) 2002 Brett Smith.
 *
 *  Written by: Brett Smith <t_magicthize@users.sourceforge.net>
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public License
 *  as published by the Free Software Foundation; either version 2 of
 *  the License, or (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

package gruntspud.ui.report;

import gruntspud.CVSChangeStatus;
import gruntspud.Constants;
import gruntspud.GruntspudContext;
import gruntspud.ui.UIUtil;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.UIManager;

import org.netbeans.lib.cvsclient.command.FileInfoContainer;
import org.netbeans.lib.cvsclient.command.commit.CommitInformation;

/**
 *  Description of the Class
 *
 *@author     magicthize
 *@created    26 May 2002
 */
public class CommitFileInfoPane
    extends DefaultFileInfoPane {
    //  Private instance variables
    private JLabel file;

    //  Private instance variables
    private JLabel type;

    //  Private instance variables
    private JLabel revision;

    //  Private instance variables
    private JLabel directory;

    /**
     * Constructor
     */
    public CommitFileInfoPane(GruntspudContext context) {
        super(context, UIUtil.getCachedIcon(Constants.ICON_TOOL_COMMIT),
              UIUtil.getCachedIcon(Constants.ICON_TOOL_SMALL_COMMIT), "File commited");
    }

    protected void createComponents() {
        setLayout(new GridBagLayout());

        Font valFont = UIManager.getFont("Label.font").deriveFont(Font.BOLD);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        UIUtil.jGridBagAdd(this, new JLabel("File: "), gbc,
                           GridBagConstraints.RELATIVE);
        gbc.weightx = 1.0;
        file = new JLabel() {
            public Dimension getPreferredSize() {
                return new Dimension(260,
                                     super.getPreferredSize().height);
            }
        };
        file.setFont(valFont);
        UIUtil.jGridBagAdd(this, file, gbc, GridBagConstraints.REMAINDER);

        gbc.weightx = 0.0;
        UIUtil.jGridBagAdd(this, new JLabel("Directory: "), gbc,
                           GridBagConstraints.RELATIVE);
        gbc.weightx = 1.0;
        directory = new JLabel();
        directory.setFont(valFont);
        UIUtil.jGridBagAdd(this, directory, gbc, GridBagConstraints.REMAINDER);

        gbc.weightx = 0.0;
        UIUtil.jGridBagAdd(this, new JLabel("Type: "), gbc,
                           GridBagConstraints.RELATIVE);
        gbc.weightx = 1.0;
        type = new JLabel();
        type.setFont(valFont);
        UIUtil.jGridBagAdd(this, type, gbc, GridBagConstraints.REMAINDER);

        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        UIUtil.jGridBagAdd(this, new JLabel("New revision: "), gbc,
                           GridBagConstraints.RELATIVE);
        gbc.weightx = 1.0;
        revision = new JLabel();
        revision.setFont(valFont);
        UIUtil.jGridBagAdd(this, revision, gbc, GridBagConstraints.REMAINDER);
    }

    /**
     *  Description of the Method
     *
     *@return    Description of the Return Value
     */
    public Object getInfoValueForInfoContainer(FileInfoContainer container) {
        return CVSChangeStatus.getStatusForCode(((CommitInformation)container).getType().charAt(0),
            CVSChangeStatus.COMMIT_CHANGE_STATUS);
    }

    /**
     *  Description of the Method
     *
     *@return    Description of the Return Value
     */
    public Class getInfoClass() {
        return CVSChangeStatus.class;
    }

    /**
     *  Description of the Method
     *
     *@return    Description of the Return Value
     */
    public void setFileInfo(FileInfoContainer container) {
        CommitInformation info = (CommitInformation) container;
        file.setText(info.getFile().getName());
        file.setToolTipText(container.getFile().getAbsolutePath());
        directory.setText(info.getFile().getParentFile().getName());
        directory.setToolTipText(info.getFile().getParentFile().getAbsolutePath());
        type.setText(info.getType());
        revision.setText(info.getRevision());
    }
}
