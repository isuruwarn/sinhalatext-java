package org.warn.sinhala.texteditor.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.warn.sinhala.texteditor.util.GlobalConstants;



@SuppressWarnings("serial")
public class SinhalaButton extends JButton {
	
	public SinhalaButton( String name, String toolTip, String actionCommand, ActionListener listener ) {
		this.setText(name);
		this.setToolTipText(toolTip);
		this.setFont( new Font( GlobalConstants.ISKOOLA_POTA_FONT_TYPE, java.awt.Font.PLAIN, GlobalConstants.BUTTON_FONT_SIZE ) );
		this.setActionCommand(actionCommand);
		this.addActionListener(listener);
		this.setBackground( new Color( 242, 242, 245 ) );
		this.setMargin( new Insets( 5, 5, 5, 5 ) );
	}
	
}
