package org.warn.sinhala.texteditor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.text.StyledEditorKit;

import org.warn.sinhala.texteditor.ui.Container;



public class FontSizeActionListener implements ActionListener {
	
	
	private Container container;
	
	
	public FontSizeActionListener( Container container ) {
		this.container = container;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		int size = 0;
		Object obj = e.getSource();
		
		if( obj instanceof JComboBox ) {
			
			JComboBox<String> selectBox = (JComboBox<String>) e.getSource();
			size = Integer.parseInt( (String) selectBox.getSelectedItem() );
			
		} else if( obj instanceof JMenuItem ) {
			size = Integer.parseInt( e.getActionCommand() );
		}
		
		Action fontAction = new StyledEditorKit.FontSizeAction( String.valueOf(size), size );
		fontAction.actionPerformed(e);
		container.setFontSize(size);
	}
	
}