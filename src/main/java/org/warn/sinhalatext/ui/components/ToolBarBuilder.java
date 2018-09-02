package org.warn.sinhalatext.ui.components;

import javax.swing.JButton;
import javax.swing.JToolBar;



public class ToolBarBuilder {
	
	public static JToolBar createToolBar( String name, JButton... buttons ) {
		
		JToolBar toolBar = new JToolBar();
		toolBar.setName(name);
		toolBar.setFloatable(false);
		
		for( JButton button: buttons ) {
			toolBar.add( button );
		}
		
		return toolBar;
	}
	
	
	
}
