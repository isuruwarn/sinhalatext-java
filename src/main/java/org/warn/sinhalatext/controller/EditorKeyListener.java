package org.warn.sinhalatext.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JTextPane;

import org.warn.sinhalatext.model.TextHandler;
import org.warn.sinhalatext.util.GlobalConstants;





public class EditorKeyListener implements KeyListener {
	
	private JTextPane textPane;
	private ButtonGroup modeBtnGroup;
	
	
	public EditorKeyListener( JTextPane textPane, ButtonGroup modeBtnGroup ) {
		this.textPane = textPane;
		this.modeBtnGroup = modeBtnGroup;
	}
	
	
	
	public void keyTyped(KeyEvent e) {
		
		String inputKey = String.valueOf( e.getKeyChar() );
		String mode =  modeBtnGroup.getSelection().getActionCommand();
		
		
		if( mode.equals( GlobalConstants.SINHALA_MODE_BTN ) ) {
			
			if( inputKey.matches("[A-Za-z]") || 
				inputKey.equals( "`" ) || 
				inputKey.equals( "~" ) || 
				inputKey.equals( "@" ) || 
				inputKey.equals( "^" ) || 
				inputKey.equals( "_" ) || 
				inputKey.equals( "[" ) || 
				inputKey.equals( "{" ) || 
				inputKey.equals( "]" ) || 
				inputKey.equals( "}" ) || 
				inputKey.equals( "\\" ) || 
				inputKey.equals( "|" ) || 
				inputKey.equals( "<" ) || 
				inputKey.equals( ">" ) ) {
				
				e.consume(); // prevents default action, to stop original character from being printed.
				TextHandler.insertText( inputKey, textPane );
			}
			
		}
		
	}
	
	
	public void keyPressed( KeyEvent e ) {}
	
	public void keyReleased(KeyEvent e) {}
	
}


