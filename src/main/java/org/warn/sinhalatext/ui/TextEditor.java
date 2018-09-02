package org.warn.sinhalatext.ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class TextEditor {
	
    public static void main(String[] args) {

    	try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
    	
    	javax.swing.SwingUtilities.invokeLater( new Runnable() {
    		public void run() {
            	new Container();
            }
        });
    }

}
