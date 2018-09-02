package org.warn.sinhala.texteditor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;

import org.warn.sinhala.texteditor.model.TextHandler;
import org.warn.sinhala.texteditor.util.GlobalConstants;



public class EditActionListener implements ActionListener {
	
	
	private JTextPane textPane;
	
	
	public EditActionListener( JTextPane textPane ) {
		this.textPane = textPane;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch(command) {
			
			case GlobalConstants.CLEAR_ACTION:
				TextHandler.clearAll( textPane );
				break;
			
			case GlobalConstants.SELECT_ALL_ACTION:
				TextHandler.selectAll( textPane );
				break;
				
			case GlobalConstants.CUT_ACTION:
				TextHandler.cut( textPane );
				break;
			
			case GlobalConstants.COPY_ACTION:
				TextHandler.copyToClipboard( textPane );
				break;
			
			case GlobalConstants.PASTE_ACTION:
				TextHandler.paste( textPane );
				break;
		}
	}

}
