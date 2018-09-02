package org.warn.sinhala.texteditor.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

import org.warn.sinhala.texteditor.controller.EditActionListener;
import org.warn.sinhala.texteditor.controller.EditorKeyListener;
import org.warn.sinhala.texteditor.controller.EditorUndoableEditListener;
import org.warn.sinhala.texteditor.controller.FileActionListener;
import org.warn.sinhala.texteditor.controller.FontSizeActionListener;
import org.warn.sinhala.texteditor.controller.HelpActionListener;
import org.warn.sinhala.texteditor.controller.SinhalaButtonActionListener;
import org.warn.sinhala.texteditor.controller.UndoActionListener;
import org.warn.sinhala.texteditor.model.FileHandler;
import org.warn.sinhala.texteditor.model.Symbol;
import org.warn.sinhala.texteditor.ui.components.CommandButton;
import org.warn.sinhala.texteditor.ui.components.MenuBarBuilder;
import org.warn.sinhala.texteditor.ui.components.SinhalaButton;
import org.warn.sinhala.texteditor.ui.components.ToolBarBuilder;
import org.warn.sinhala.texteditor.util.GlobalConstants;



public class Container {
	
	

	
	private JFrame editorFrame;
	private JComboBox<String> selectFontSize;

	
	
	public Container() {
		
		CommandButton undoBtn = new CommandButton( GlobalConstants.UNDO_ACTION, GlobalConstants.UNDO_IMG  );
		CommandButton redoBtn = new CommandButton( GlobalConstants.REDO_ACTION, GlobalConstants.REDO_IMG  );
		undoBtn.setEnabled(false);
		redoBtn.setEnabled(false);
		EditorUndoableEditListener undoableEditListener = new EditorUndoableEditListener( undoBtn, redoBtn );
		UndoActionListener undoActionListener = new UndoActionListener( undoableEditListener );
		undoBtn.addActionListener(undoActionListener);
		redoBtn.addActionListener(undoActionListener);
		
		// main text area
		JTextPane textPane = new JTextPane();
		textPane.setEditable(true);
		textPane.setFont( new Font( GlobalConstants.ISKOOLA_POTA_FONT_TYPE, java.awt.Font.PLAIN, GlobalConstants.MAIN_TEXT_FONT_SIZE ) );
		textPane.setPreferredSize( new Dimension( GlobalConstants.PREF_TEXTAREA_WIDTH, GlobalConstants.PREF_TEXTAREA_HEIGHT ) );
		textPane.setMinimumSize( new Dimension( GlobalConstants.MIN_TEXTAREA_WIDTH, GlobalConstants.MIN_TEXTAREA_HEIGHT ) );
		
		textPane.getDocument().addUndoableEditListener( undoableEditListener );
		JScrollPane textScrollPane = new JScrollPane(textPane);
		
		FileHandler fileHandler = new FileHandler();
		FileActionListener fileActionListener = new FileActionListener( editorFrame, textPane, this, fileHandler );
		
		CommandButton newFileBtn = new CommandButton( GlobalConstants.NEW_FILE_ACTION, fileActionListener, GlobalConstants.NEW_FILE_IMG  );
		CommandButton openFileBtn = new CommandButton( GlobalConstants.OPEN_ACTION, fileActionListener, GlobalConstants.OPEN_FILE_IMG  );
		CommandButton saveBtn = new CommandButton( GlobalConstants.SAVE_ACTION, fileActionListener, GlobalConstants.SAVE_IMG  );
		CommandButton printBtn = new CommandButton( GlobalConstants.PRINT_ACTION, fileActionListener, GlobalConstants.PRINT_IMG  );
		CommandButton printPreviewBtn = new CommandButton( GlobalConstants.PRINT_PREVIEW_ACTION, fileActionListener, GlobalConstants.PRINT_PREVIEW_IMG  );
		
		EditActionListener editActionListener = new EditActionListener( textPane );
		CommandButton clearBtn = new CommandButton( GlobalConstants.CLEAR_ACTION, editActionListener, GlobalConstants.CLEAR_IMG  );
		CommandButton selectAllBtn = new CommandButton( GlobalConstants.SELECT_ALL_ACTION, editActionListener, GlobalConstants.SELECT_ALL_IMG  );
		CommandButton cutBtn = new CommandButton( GlobalConstants.CUT_ACTION, editActionListener, GlobalConstants.CUT_IMG );
		CommandButton copyBtn = new CommandButton( GlobalConstants.COPY_ACTION, editActionListener, GlobalConstants.COPY_IMG );
		CommandButton pasteBtn = new CommandButton( GlobalConstants.PASTE_ACTION, editActionListener, GlobalConstants.PASTE_IMG );
		CommandButton boldBtn = new CommandButton( GlobalConstants.BOLD_BTN, new StyledEditorKit.BoldAction(), GlobalConstants.BOLD_IMG );
		CommandButton italicBtn = new CommandButton( GlobalConstants.ITALIC_BTN, new StyledEditorKit.ItalicAction(), GlobalConstants.ITALIC_IMG );
		CommandButton underlineBtn = new CommandButton( GlobalConstants.UNDERLINE_BTN, new StyledEditorKit.UnderlineAction(), GlobalConstants.UNDERLINE_IMG );
		CommandButton alignLeftBtn = new CommandButton( GlobalConstants.ALIGN_LEFT_BTN, new StyledEditorKit.AlignmentAction("Align Left", StyleConstants.ALIGN_LEFT), GlobalConstants.LEFT_ALIGN_IMG );
		CommandButton centerAlignBtn = new CommandButton( GlobalConstants.ALIGN_CENTER_BTN, new StyledEditorKit.AlignmentAction("Center Align", StyleConstants.ALIGN_CENTER), GlobalConstants.CENTER_ALIGN_IMG );
		CommandButton alignRightBtn = new CommandButton( GlobalConstants.ALIGN_RIGHT_BTN, new StyledEditorKit.AlignmentAction("Align Right", StyleConstants.ALIGN_RIGHT), GlobalConstants.RIGHT_ALIGN_IMG );
		CommandButton justifiedBtn = new CommandButton( GlobalConstants.JUSTIFIED_BTN, new StyledEditorKit.AlignmentAction("Justified", StyleConstants.ALIGN_JUSTIFIED), GlobalConstants.JUSTIFIED_IMG );
		
		JToolBar formattingToolBar = ToolBarBuilder.createToolBar( GlobalConstants.FORMATTING_TOOLBAR, newFileBtn, openFileBtn, saveBtn, printBtn, printPreviewBtn );
		
		formattingToolBar.addSeparator();
		formattingToolBar.add(undoBtn);
		formattingToolBar.add(redoBtn);
		
		formattingToolBar.addSeparator();
		formattingToolBar.add(selectAllBtn);
		formattingToolBar.add(cutBtn);
		formattingToolBar.add(copyBtn);
		formattingToolBar.add(pasteBtn);
		formattingToolBar.add(clearBtn);
		formattingToolBar.add(boldBtn);
		formattingToolBar.add(italicBtn);
		formattingToolBar.add(underlineBtn);
		
		formattingToolBar.addSeparator();
		formattingToolBar.add(alignLeftBtn);
		formattingToolBar.add(centerAlignBtn);
		formattingToolBar.add(alignRightBtn);
		formattingToolBar.add(justifiedBtn );
		
		JLabel fontSizeLabel = new JLabel( GlobalConstants.FONTSIZE_MENU );
		fontSizeLabel.setBorder( new EmptyBorder(0, 10, 0, 5) );
		
		String [] fontSizes = {
				GlobalConstants.FONTSIZE_10_ACTION, 
				GlobalConstants.FONTSIZE_12_ACTION, 
				GlobalConstants.FONTSIZE_14_ACTION, 
				GlobalConstants.FONTSIZE_16_ACTION, 
				GlobalConstants.FONTSIZE_18_ACTION, 
				GlobalConstants.FONTSIZE_20_ACTION, 
				GlobalConstants.FONTSIZE_22_ACTION, 
				GlobalConstants.FONTSIZE_24_ACTION, 
				GlobalConstants.FONTSIZE_26_ACTION, 
				GlobalConstants.FONTSIZE_28_ACTION 
				};
		selectFontSize = new JComboBox<String>(fontSizes);
		selectFontSize.setSelectedItem( String.valueOf( GlobalConstants.MAIN_TEXT_FONT_SIZE ) );
		FontSizeActionListener fontSizeActionListener = new FontSizeActionListener( this );
		selectFontSize.addActionListener( fontSizeActionListener );
		
		JRadioButton sinhalaModeBtn = new JRadioButton( GlobalConstants.SINHALA_MODE_BTN );
		sinhalaModeBtn.setActionCommand( GlobalConstants.SINHALA_MODE_BTN );
		sinhalaModeBtn.setSelected(true);
		sinhalaModeBtn.setBorder( new EmptyBorder(0, 10, 0, 3) );
		
		JRadioButton englishModeBtn = new JRadioButton( GlobalConstants.ENGLISH_MODE_BTN );
	    englishModeBtn.setActionCommand( GlobalConstants.ENGLISH_MODE_BTN );
	    englishModeBtn.setBorder( new EmptyBorder(0, 3, 0, 10) );
	    
	    //Group the radio buttons.
	    ButtonGroup modeBtnGroup = new ButtonGroup();
	    modeBtnGroup.add(sinhalaModeBtn);
	    modeBtnGroup.add(englishModeBtn);
	    
	    
	    JToolBar fontToolBar = new JToolBar( GlobalConstants.FONT_TOOLBAR );
	    fontToolBar.setFloatable(false);
	    
	    //fontToolBar.add(fontSizeLabel);
	    fontToolBar.add(selectFontSize);
	    fontToolBar.addSeparator();
	    fontToolBar.add(sinhalaModeBtn);
	    fontToolBar.add(englishModeBtn);
	    
	    SinhalaButtonActionListener sinhalaBtnListener = new SinhalaButtonActionListener( textPane ); 
		
		// additional sinhala characters
		SinhalaButton ksha = new SinhalaButton( Symbol.KSHA.getUnicodeValue(), Symbol.KSHA.name(), Symbol.KSHA.getKeyCode(),  sinhalaBtnListener );
		SinhalaButton thalujaNasikya = new SinhalaButton( Symbol.THALUJA_NASIKAYA.getUnicodeValue(), Symbol.THALUJA_NASIKAYA.name(), Symbol.THALUJA_NASIKAYA.getKeyCode(),  sinhalaBtnListener );
		SinhalaButton sanyogaNasikaya = new SinhalaButton( Symbol.SANYOGA_NASIKAYA.getUnicodeValue(), Symbol.SANYOGA_NASIKAYA.name(), Symbol.SANYOGA_NASIKAYA.getKeyCode(),  sinhalaBtnListener );
		SinhalaButton kombuDeka = new SinhalaButton( Symbol.KOMBU_DEKA.getUnicodeValue(), Symbol.KOMBU_DEKA.name(), Symbol.KOMBU_DEKA.getKeyCode(),  sinhalaBtnListener );
		SinhalaButton kombuvaHaGayanuKitta = new SinhalaButton( Symbol.KOMBUVA_HAA_GAYANUKITTA.getUnicodeValue(), Symbol.KOMBUVA_HAA_GAYANUKITTA.name(), Symbol.KOMBUVA_HAA_GAYANUKITTA.getKeyCode(),  sinhalaBtnListener );
		SinhalaButton digaGaetaPilla = new SinhalaButton( Symbol.DIGA_GAETTA_PILLA.getUnicodeValue(), Symbol.DIGA_GAETTA_PILLA.name(), Symbol.DIGA_GAETTA_PILLA.getKeyCode(),  sinhalaBtnListener );
		SinhalaButton gayanukitta = new SinhalaButton( Symbol.GAYANUKITTA.getUnicodeValue(), Symbol.GAYANUKITTA.name(), Symbol.GAYANUKITTA.getKeyCode(),  sinhalaBtnListener );
		SinhalaButton digaGayanukitta = new SinhalaButton( Symbol.DIGA_GAYANUKITTA.getUnicodeValue(), Symbol.DIGA_GAYANUKITTA.name(), Symbol.DIGA_GAYANUKITTA.getKeyCode(),  sinhalaBtnListener );
		
		// add buttons to additional chars tool bar
		JToolBar additionalCharsToolBar = ToolBarBuilder.createToolBar( GlobalConstants.ADDITIONAL_CHARS_TOOLBAR, ksha, thalujaNasikya, sanyogaNasikaya, kombuDeka, kombuvaHaGayanuKitta, digaGaetaPilla, gayanukitta, digaGayanukitta );
		
		
		// layout manager configurations
		GridBagConstraints fmtToolBarGridCons = new GridBagConstraints();
		fmtToolBarGridCons.gridx = 0;
		fmtToolBarGridCons.gridy = 0;
		fmtToolBarGridCons.anchor = GridBagConstraints.LINE_START;
		
		GridBagConstraints fontToolBarGridCons = new GridBagConstraints();
		fontToolBarGridCons.gridx = 0;
		fontToolBarGridCons.gridy = 1;
		fontToolBarGridCons.anchor = GridBagConstraints.LINE_START;
		
		GridBagConstraints addCharToolBarGridCons = new GridBagConstraints();
		addCharToolBarGridCons.gridx = 0;
		addCharToolBarGridCons.gridy = 2;
		addCharToolBarGridCons.anchor = GridBagConstraints.LINE_START;
		addCharToolBarGridCons.insets = new Insets(0,0,10,0);
		
		GridBagConstraints textPaneGridCons = new GridBagConstraints();
		textPaneGridCons.gridx = 0;
		textPaneGridCons.gridy = 3;
		textPaneGridCons.insets = new Insets(0,0,10,0);
		
		EditorKeyListener editorKeyListener = new EditorKeyListener( textPane, modeBtnGroup );
	    textPane.addKeyListener( editorKeyListener );
	    
		HelpActionListener helpActionListener = new HelpActionListener( editorFrame );
		
		// construct the menu bar
		JMenuBar menuBar = MenuBarBuilder.createMainMenuBar( fileActionListener, editActionListener, helpActionListener, undoActionListener, fontSizeActionListener );
		
		// main panel
		JPanel editorPanel = new JPanel();
		editorPanel.setLayout( new GridBagLayout() );
		editorPanel.add( formattingToolBar, fmtToolBarGridCons );
		editorPanel.add( fontToolBar, fontToolBarGridCons );
		editorPanel.add( additionalCharsToolBar, addCharToolBarGridCons );
		editorPanel.add( textScrollPane, textPaneGridCons ); 
		editorPanel.setOpaque(true);
		JScrollPane mainScrollPane = new JScrollPane(editorPanel);
		
		// frame for holding everything
		editorFrame = new JFrame( GlobalConstants.TITLE );
		editorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editorFrame.setPreferredSize( new Dimension( GlobalConstants.PREF_FRAME_WIDTH, GlobalConstants.PREF_FRAME_HEIGHT ) );
		editorFrame.setMinimumSize( new Dimension( GlobalConstants.MIN_FRAME_WIDTH, GlobalConstants.MIN_FRAME_HEIGHT ) );
		editorFrame.setJMenuBar(menuBar);
		editorFrame.add( mainScrollPane );
		editorFrame.pack();
		textPane.requestFocusInWindow(); // has to be called after pack() and before setVisible()
		editorFrame.setVisible(true);
		
	}
	
	
	
	
	public void setFrameTitle( String title ) {
		if( title != null && !title.equals("") ) {
			editorFrame.setTitle( GlobalConstants.TITLE + " - " + title );
		}
	}
    
	
	
	
	public void setFontSize( int fontSize ) {
		selectFontSize.setSelectedItem( String.valueOf(fontSize) );
	}
	
	
}
