package org.warn.sinhalatext.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;

import org.warn.sinhalatext.model.TextHandler;




public class SinhalaButtonActionListener implements ActionListener {
	
	
	private JTextPane textPane;
	
	public SinhalaButtonActionListener( JTextPane textPane ) {
		this.textPane = textPane;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String input = e.getActionCommand();
		TextHandler.insertText( input, textPane );
	}

}
