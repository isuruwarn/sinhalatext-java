package org.warn.sinhala.texteditor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.warn.sinhala.texteditor.ui.components.EditorHelpDialog;
import org.warn.sinhala.texteditor.util.GlobalConstants;

public class HelpActionListener implements ActionListener {
	
	private JFrame frame;
	
	
	public HelpActionListener(JFrame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch(command) {
			
			case GlobalConstants.HELP_ACTION:
				new EditorHelpDialog( frame );
				break;
				
			case GlobalConstants.ABOUT_ACTION: 
				JOptionPane.showMessageDialog( frame, GlobalConstants.ABOUT_MESSAGE, GlobalConstants.ABOUT_TITLE, JOptionPane.INFORMATION_MESSAGE );
				break;
		}
		
	}
	
}