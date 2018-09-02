package org.warn.sinhala.texteditor.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JTextPane;



public class PrintableHandler implements Printable {

	
	private JTextPane textPane;
	
	
	public PrintableHandler( JTextPane textPane ) {
		this.textPane = textPane;
	}
	
	
	
	@Override
	public int print( Graphics graphics, PageFormat pageFormat, int pageIndex ) throws PrinterException {
		
		if( pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
		
		Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate( pageFormat.getImageableX(), pageFormat.getImageableY());
        textPane.printAll( graphics );
        return PAGE_EXISTS;
	}
	
	
	public static void print( JTextPane textPane ) {
		
		PrinterJob job = PrinterJob.getPrinterJob();
		PrintableHandler printable = new PrintableHandler( textPane );
        job.setPrintable( printable );
        boolean print = job.printDialog();
        
        if( print ) {
            try {
                 job.print();
            } catch (PrinterException ex) {
            	ex.printStackTrace();
            }
        }
	}

}
