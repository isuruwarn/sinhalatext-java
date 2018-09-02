package org.warn.sinhalatext.controller;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import org.warn.sinhalatext.ui.components.CommandButton;



public class EditorUndoableEditListener  implements UndoableEditListener {
	
	
	private UndoManager undo;
	private CommandButton undoBtn;
	private CommandButton redoBtn;
	
	
	public EditorUndoableEditListener( CommandButton undoBtn, CommandButton redoBtn ) {
		undo = new UndoManager();
		this.undoBtn = undoBtn;
		this.redoBtn = redoBtn;
	}
	
	
	public void undoableEditHappened(UndoableEditEvent e) {
		undo.addEdit( e.getEdit() ); // remember the edit and update the menus
		updateUndoRedoState();
	}
	
	
	public void undo() {
		undo.undo();
		updateUndoRedoState();
	}
	
	
	
	public void redo() {
		undo.redo();
		updateUndoRedoState();
	}
	
	
	
	private void updateUndoRedoState() {
		
		if ( undo.canUndo() ) {
			undoBtn.setEnabled(true);
		} else {
			undoBtn.setEnabled(false);
		}

		if ( undo.canRedo() ) {
			redoBtn.setEnabled(true);
		} else {
			redoBtn.setEnabled(false);
		}
	}
	
}
