package org.warn.sinhala.texteditor.ui.components;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.warn.sinhala.texteditor.util.GlobalConstants;



@SuppressWarnings("serial")
public class EditorHelpDialog extends JDialog {

	public EditorHelpDialog( Frame frame ) {
		super( frame, true);
		JLabel coloredLabel = new JLabel( GlobalConstants.HELP_MESSAGE );
		coloredLabel.setFont( new Font( GlobalConstants.ISKOOLA_POTA_FONT_TYPE, java.awt.Font.PLAIN, GlobalConstants.MAIN_TEXT_FONT_SIZE ) );
		JOptionPane.showMessageDialog( frame, coloredLabel, GlobalConstants.HELP_TITLE, JOptionPane.PLAIN_MESSAGE );
	}
}
