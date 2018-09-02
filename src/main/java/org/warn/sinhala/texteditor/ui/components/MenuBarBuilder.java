package org.warn.sinhala.texteditor.ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.text.StyledEditorKit;

import org.warn.sinhala.texteditor.controller.EditActionListener;
import org.warn.sinhala.texteditor.controller.FileActionListener;
import org.warn.sinhala.texteditor.controller.FontSizeActionListener;
import org.warn.sinhala.texteditor.controller.HelpActionListener;
import org.warn.sinhala.texteditor.controller.UndoActionListener;
import org.warn.sinhala.texteditor.util.GlobalConstants;



public class MenuBarBuilder {

	
	private static final String OS_PROP_NAME = "os.name";
	private static final String MAC_OS_NAME = "mac";

	
	public static JMenuBar createMainMenuBar( 	FileActionListener fileActionListener, EditActionListener editActionListener, 
			HelpActionListener helpActionListener, UndoActionListener undoActionListener, FontSizeActionListener fontSizeActionListener ) {
		
		int controlCommandMask = ActionEvent.CTRL_MASK;
		String osType = System.getProperty( OS_PROP_NAME ).toLowerCase();
		if( osType.startsWith( MAC_OS_NAME ) ) {
			controlCommandMask = ActionEvent.META_MASK;
		}
		
		// file menu
		JMenuItem newFile = new JMenuItem( GlobalConstants.NEW_FILE_ACTION, KeyEvent.VK_N );
		JMenuItem open = new JMenuItem( GlobalConstants.OPEN_ACTION, KeyEvent.VK_O );
		JMenuItem save = new JMenuItem(  GlobalConstants.SAVE_ACTION );
		JMenuItem saveAs = new JMenuItem(  GlobalConstants.SAVEAS_ACTION );
		JMenuItem printPreview = new JMenuItem( GlobalConstants.PRINT_PREVIEW_ACTION );
		JMenuItem print = new JMenuItem( GlobalConstants.PRINT_ACTION );
		JMenuItem exit = new JMenuItem( GlobalConstants.EXIT_ACTION );
		JMenu fileMenu = createSubMenu( GlobalConstants.FILE_MENU, fileActionListener, newFile, open, save, saveAs, printPreview, print, exit );
		
		newFile.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, controlCommandMask ) );
		open.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, controlCommandMask ) );
		save.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, controlCommandMask ) );
		print.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_P, controlCommandMask ) );
		
		
		// edit menu
		JMenuItem cut = new JMenuItem( GlobalConstants.CUT_ACTION );
		JMenuItem copy = new JMenuItem( GlobalConstants.COPY_ACTION );
		JMenuItem paste = new JMenuItem( GlobalConstants.PASTE_ACTION );
		JMenuItem selectAll = new JMenuItem( GlobalConstants.SELECTALL_ACTION );
		JMenuItem clear = new JMenuItem( GlobalConstants.CLEAR_ACTION );
		
		cut.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_X, controlCommandMask ) );
		copy.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_C, controlCommandMask ) );
		paste.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_V, controlCommandMask ) );
		selectAll.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_A, controlCommandMask ) );
		clear.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_BACK_SPACE, controlCommandMask ) );
		
		JMenu editMenu = createSubMenu( GlobalConstants.EDIT_MENU, editActionListener, cut, copy, paste, selectAll, clear );
		
		// add undo and redo with separator
		JMenuItem undo = new JMenuItem( GlobalConstants.UNDO_ACTION );
		JMenuItem redo = new JMenuItem( GlobalConstants.REDO_ACTION );
		undo.addActionListener( undoActionListener );
		redo.addActionListener( undoActionListener );
		undo.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Z, controlCommandMask ) );
		redo.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Y, controlCommandMask ) );
		editMenu.addSeparator();
		editMenu.add(undo);
		editMenu.add(redo);
		
		// formatting menu
		JMenuItem bold = new JMenuItem( new StyledEditorKit.BoldAction() );
		JMenuItem italic = new JMenuItem( new StyledEditorKit.ItalicAction() );
		JMenuItem underline = new JMenuItem( new StyledEditorKit.UnderlineAction() );
		
		bold.setText( GlobalConstants.BOLD_ACTION );
		italic.setText( GlobalConstants.ITALIC_ACTION );
		underline.setText( GlobalConstants.UNDERLINE_ACTION );
		
		bold.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_B, controlCommandMask ) );
		italic.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_I, controlCommandMask ) );
		underline.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_U, controlCommandMask ) );
		
		JMenuItem iskoolaPotaFont = new JMenuItem( new StyledEditorKit.FontFamilyAction( GlobalConstants.ISKOOLA_POTA_FONT_TYPE, GlobalConstants.ISKOOLA_POTA_FONT_TYPE ) );
		JMenuItem verdanaFont = new JMenuItem( new StyledEditorKit.FontFamilyAction( GlobalConstants.VERDANA_FONT_TYPE, GlobalConstants.VERDANA_FONT_TYPE ) );
		JMenu font = new JMenu( GlobalConstants.FONT_MENU );
		font.add(iskoolaPotaFont);
		font.add(verdanaFont);
		
		JMenuItem fontSize10 = new JMenuItem( GlobalConstants.FONTSIZE_10_ACTION );
		JMenuItem fontSize12 = new JMenuItem( GlobalConstants.FONTSIZE_12_ACTION );
		JMenuItem fontSize14 = new JMenuItem( GlobalConstants.FONTSIZE_14_ACTION );
		JMenuItem fontSize16 = new JMenuItem( GlobalConstants.FONTSIZE_16_ACTION );
		JMenuItem fontSize18 = new JMenuItem( GlobalConstants.FONTSIZE_18_ACTION );
		JMenuItem fontSize20 = new JMenuItem( GlobalConstants.FONTSIZE_20_ACTION );
		JMenuItem fontSize22 = new JMenuItem( GlobalConstants.FONTSIZE_22_ACTION );
		JMenuItem fontSize24 = new JMenuItem( GlobalConstants.FONTSIZE_24_ACTION );
		JMenuItem fontSize26 = new JMenuItem( GlobalConstants.FONTSIZE_26_ACTION );
		JMenuItem fontSize28 = new JMenuItem( GlobalConstants.FONTSIZE_28_ACTION );
		JMenu fontSize = createSubMenu( GlobalConstants.FONTSIZE_MENU, fontSizeActionListener, fontSize10, fontSize12, fontSize14, fontSize16, fontSize18, fontSize20, fontSize22, fontSize24, fontSize26, fontSize28 );
		
		JMenuItem blackFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.BLACK_FONTCOLOR_ACTION, Color.BLACK ) );
		JMenuItem blueFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.BLUE_FONTCOLOR_ACTION, Color.BLUE ) );
		JMenuItem cyanFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.CYAN_FONTCOLOR_ACTION, Color.CYAN ) );
		JMenuItem darkGrayFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.DARK_GRAY_FONTCOLOR_ACTION, Color.DARK_GRAY ) );
		JMenuItem grayFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.GRAY_FONTCOLOR_ACTION, Color.GRAY ) );
		JMenuItem greenFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.GREEN_FONTCOLOR_ACTION, Color.GREEN ) );
		JMenuItem lightGrayFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.LIGHT_GRAY_FONTCOLOR_ACTION, Color.LIGHT_GRAY ) );
		JMenuItem magentaFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.MAGENTA_FONTCOLOR_ACTION, Color.MAGENTA ) );
		JMenuItem orangeFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.ORANGE_FONTCOLOR_ACTION, Color.ORANGE ) );
		JMenuItem pinkFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.PINK_FONTCOLOR_ACTION, Color.PINK ) );
		JMenuItem redFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.RED_FONTCOLOR_ACTION, Color.RED ) );
		JMenuItem whiteFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.WHITE_FONTCOLOR_ACTION, Color.WHITE ) );
		JMenuItem yellowFontColor = new JMenuItem( new StyledEditorKit.ForegroundAction( GlobalConstants.YELLOW_FONTCOLOR_ACTION, Color.YELLOW ) );
		
		blackFontColor.setIcon( new ColorIcon( Color.BLACK ) );
		blueFontColor.setIcon( new ColorIcon( Color.BLUE ) );
		cyanFontColor.setIcon( new ColorIcon( Color.CYAN ) );
		darkGrayFontColor.setIcon( new ColorIcon( Color.DARK_GRAY ) );
		grayFontColor.setIcon( new ColorIcon( Color.GRAY ) );
		greenFontColor.setIcon( new ColorIcon( Color.GREEN ) );
		lightGrayFontColor.setIcon( new ColorIcon( Color.LIGHT_GRAY ) );
		magentaFontColor.setIcon( new ColorIcon( Color.MAGENTA ) );
		orangeFontColor.setIcon( new ColorIcon( Color.ORANGE ) );
		pinkFontColor.setIcon( new ColorIcon( Color.PINK ) );
		redFontColor.setIcon( new ColorIcon( Color.RED ) );
		whiteFontColor.setIcon( new ColorIcon( Color.WHITE ) );
		yellowFontColor.setIcon( new ColorIcon( Color.YELLOW ) );
		
		JMenu fontColor = createSubMenu( GlobalConstants.FONTCOLOR_MENU, blackFontColor, blueFontColor, cyanFontColor, darkGrayFontColor, grayFontColor, 
				greenFontColor, lightGrayFontColor, magentaFontColor, orangeFontColor, pinkFontColor, redFontColor, whiteFontColor, yellowFontColor );
		
		JMenu formatMenu = createSubMenu( GlobalConstants.FMT_MENU, bold, italic, underline, font, fontSize, fontColor );
		
		// help menu
		JMenuItem help = new JMenuItem( GlobalConstants.HELP_ACTION );
		JMenuItem about = new JMenuItem( GlobalConstants.ABOUT_ACTION );
		JMenu helpMenu = createSubMenu( GlobalConstants.HELP_MENU, helpActionListener, help, about );
		
		// create menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(formatMenu);
		menuBar.add(helpMenu);
		
		return menuBar;
	}
	
	
	
	
	private static JMenu createSubMenu( String name, JMenuItem... items ) {
		
		JMenu subMenu = new JMenu( name );
		for( JMenuItem menuItem: items ) {
			subMenu.add(menuItem);
		}
		return subMenu;
	}
	
	
	private static JMenu createSubMenu( String name, ActionListener lisener, JMenuItem... items ) {
		
		JMenu subMenu = new JMenu( name );
		for( JMenuItem menuItem: items ) {
			menuItem.addActionListener(lisener);
			subMenu.add(menuItem);	
		}
		return subMenu;
	}
	
	
	
	static class ColorIcon implements Icon {

	    private Color fillColor;
	    private int width;
	    private int height;
	    
	    public ColorIcon( Color color ) {
	        fillColor = color;
	        this.width = 15;
	        this.height = 10;
	    }

	    @Override
	    public void paintIcon( Component c, Graphics g, int x, int y ) {
	        if (fillColor != null) {
	            g.setColor(fillColor);
	            g.fillRect( 3, 3, this.width, this.height );
	        }
	    }

	    @Override
	    public int getIconWidth() {
	        return width;
	    }

	    @Override
	    public int getIconHeight() {
	        return height;
	    }
	    
	}
	
	
}
