package org.warn.sinhalatext.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.warn.sinhalatext.util.GlobalConstants;



public class UndoActionListener implements ActionListener {
	
	EditorUndoableEditListener undoableEditListener;
	
	public UndoActionListener( EditorUndoableEditListener undoableEditListener ) {
		this.undoableEditListener = undoableEditListener;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch(command) {
			
			case GlobalConstants.UNDO_ACTION:
				undoableEditListener.undo();
				break;
				
			case GlobalConstants.REDO_ACTION:
				undoableEditListener.redo();
				break;
			
		}
	}

}
