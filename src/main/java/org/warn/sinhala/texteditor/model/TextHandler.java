package org.warn.sinhala.texteditor.model;

import java.util.HashMap;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;


public class TextHandler {
	
	
	private static HashMap<String, String> characterFixMap;
	
	
	static {
		characterFixMap = new HashMap<String, String>();
		characterFixMap.put( "\u0D85\u0DCF", Symbol.AAYANNA.getUnicodeValue() );
		characterFixMap.put( "\u0D85\u0DD0", Symbol.AEYANNA.getUnicodeValue() );
		characterFixMap.put( "\u0D85\u0DD1", Symbol.AEEYANNA.getUnicodeValue() );
		characterFixMap.put( "\u0D91\u0DCA", Symbol.EEYANNA.getUnicodeValue() );
		characterFixMap.put( "\u0D91\u0DD9", Symbol.AIYANNA.getUnicodeValue() );
		characterFixMap.put( "\u0D94\u0DCA", Symbol.OOYANNA.getUnicodeValue() ); 
		characterFixMap.put( "\u0D94\u0DDF", Symbol.AUYANNA.getUnicodeValue() );
		characterFixMap.put( "\u0DD9\u0DD9", Symbol.KOMBU_DEKA.getUnicodeValue() ); 
		characterFixMap.put( "\u0DD9\u0DCA", Symbol.DIGA_KOMBUVA.getUnicodeValue() ); 
		characterFixMap.put( "\u0DD9\u0DCF", Symbol.KOMBUVA_HAA_AELA_PILLA.getUnicodeValue() ); 
		characterFixMap.put( "\u0DD9\u0DCF\u0DCA", Symbol.KOMBUVA_HAA_DIGA_AELA_PILLA.getUnicodeValue() ); 
		characterFixMap.put( "\u0DD9\u0DDF", Symbol.KOMBUVA_HAA_GAYANUKITTA.getUnicodeValue() );
	}

	
	
	public static String getSymbol( String inputKey ) {
		
		for(Symbol symbol: Symbol.values() ) {
			if( symbol.getKeyCode().endsWith( inputKey ) ) {
				return symbol.getUnicodeValue();
			}
		}
		return null;
	}
	
	
	
	public static void insertText( String inputKey, JTextPane textPane ) {
		
		try {
	    	int cursorPos = textPane.getCaretPosition();
			//AttributeSet attributes = textPane.getCharacterAttributes();
	    	Element charElement = textPane.getStyledDocument().getCharacterElement( cursorPos );
			AttributeSet attributes = charElement.getAttributes();
	    	String newChar = getSymbol(inputKey);
			textPane.getDocument().insertString( cursorPos, newChar, attributes );
			fixUnicodeConversionIssues( textPane );
			
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
	public static void fixUnicodeConversionIssues( JTextPane textPane ) {
		
		int documentLength = textPane.getDocument().getLength();
		String text = "";
		try {
			text = textPane.getDocument().getText( 0, documentLength );
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		
		for( String key: characterFixMap.keySet() ) {
			
			int index = text.indexOf(key);
			int length = key.length();
			
			if( index > 0 ) {
				try {
					Element charElement = textPane.getStyledDocument().getCharacterElement( index );
					AttributeSet attributes = charElement.getAttributes();
					textPane.getDocument().remove( index, length );
					textPane.getDocument().insertString( index, characterFixMap.get(key), attributes );
					
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		}
		
		textPane.requestFocusInWindow();
	}
	
	
	
	public static void clearAll( JTextPane textPane ) {
		textPane.setText("");
		textPane.requestFocusInWindow();
	}
	
	
	
	public static void selectAll( JTextPane textPane ) {
		textPane.selectAll();
		textPane.requestFocusInWindow();
	}
	
	
	
	public static void copyToClipboard( JTextPane textPane ) {
		textPane.copy();
		textPane.requestFocusInWindow();
	}
	
	
	
	public static void paste( JTextPane textPane ) {
		textPane.paste();
		textPane.requestFocusInWindow();
	}
	
	
	
	public static void cut( JTextPane textPane ) {
		textPane.cut();
		textPane.requestFocusInWindow();
	}
	
	
}
