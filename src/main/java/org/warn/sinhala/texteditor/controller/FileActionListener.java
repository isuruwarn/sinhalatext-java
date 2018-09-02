package org.warn.sinhala.texteditor.controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import org.warn.sinhala.texteditor.exceptions.OpenFileException;
import org.warn.sinhala.texteditor.exceptions.SaveFileException;
import org.warn.sinhala.texteditor.model.FileHandler;
import org.warn.sinhala.texteditor.model.PrintableHandler;
import org.warn.sinhala.texteditor.ui.Container;
import org.warn.sinhala.texteditor.util.GlobalConstants;



public class FileActionListener implements ActionListener {
	
	
	private JFrame frame;
	private JTextPane textPane;
	private Container container;
	private FileHandler fileHandler;
	
	
	public FileActionListener( JFrame frame, JTextPane textPane, Container container, FileHandler fileHandler ) {
		this.frame = frame;
		this.textPane = textPane;
		this.container = container;
		this.fileHandler = fileHandler;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch(command) {
			
			case GlobalConstants.NEW_FILE_ACTION:
				fileHandler.newFile( textPane );
				container.setFrameTitle( GlobalConstants.NEW_FILE_TITLE );
				break;
				
			case GlobalConstants.OPEN_ACTION:
				try {
					String openedFileName = fileHandler.openFile( textPane );
					container.setFrameTitle(openedFileName);
					
				} catch (OpenFileException ofe) {
					JOptionPane.showMessageDialog( frame, ofe.getMessage(), GlobalConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE );
				}
				break;
				
			case GlobalConstants.SAVE_ACTION:
				try {
					String savedFileName = fileHandler.save( textPane.getText() );
					container.setFrameTitle(savedFileName);
					
				} catch (SaveFileException sfe1) {
					JOptionPane.showMessageDialog( frame, sfe1.getMessage(), GlobalConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE );
				}
				break;
				
			case GlobalConstants.SAVEAS_ACTION: 
				try {
					String savedAsFileName = fileHandler.saveAs( textPane.getText() );
					container.setFrameTitle(savedAsFileName);
					
				} catch (SaveFileException sfe2) {
					JOptionPane.showMessageDialog( frame, sfe2.getMessage(), GlobalConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE );
				}
				break;
				
			case GlobalConstants.PRINT_PREVIEW_ACTION: 
				JTextPane preview = new JTextPane( textPane.getStyledDocument()  );
				preview.setFont( new Font( GlobalConstants.ISKOOLA_POTA_FONT_TYPE, java.awt.Font.PLAIN, GlobalConstants.MAIN_TEXT_FONT_SIZE ) );
				preview.setPreferredSize( new Dimension( GlobalConstants.PREF_TEXTAREA_WIDTH, GlobalConstants.PREF_TEXTAREA_HEIGHT ) );
				preview.setMinimumSize( new Dimension( GlobalConstants.MIN_TEXTAREA_WIDTH, GlobalConstants.MIN_TEXTAREA_HEIGHT ) );
				preview.setEditable(false);
				JOptionPane.showMessageDialog( frame, preview, GlobalConstants.PRINT_PREVIEW_ACTION, JOptionPane.PLAIN_MESSAGE );
				preview = null;
				textPane.requestFocusInWindow();
				break;
				
			case GlobalConstants.PRINT_ACTION: 
				PrintableHandler.print(textPane);
				break;
			
			case GlobalConstants.EXIT_ACTION: 
				System.exit(0);
			
		} 
	}

}
