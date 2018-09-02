package org.warn.sinhalatext.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;

import org.warn.sinhalatext.exceptions.OpenFileException;
import org.warn.sinhalatext.exceptions.SaveFileException;
import org.warn.sinhalatext.util.GlobalConstants;



public class FileHandler {
	

	private String openFileName;
	private String openFilePath;
	private String saveFileName;
	private String saveFilePath;
	private JFileChooser fc;
	
	
	
	public FileHandler() {
		this.fc = new JFileChooser();
		FileFilter filter = new TextFileFilter();
		fc.setFileFilter(filter);
	}
	
	
	
	public void newFile( JTextPane textPane ) {
    	openFileName = null;
    	openFilePath = null;
    	saveFileName = null;
    	saveFilePath = null;
		textPane.setText(null);
	}
	
	
	public String openFile( JTextPane textPane ) throws OpenFileException {
		
		int returnVal = fc.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	File file = fc.getSelectedFile();
        	openFileName = file.getName();
        	openFilePath = file.getPath();
        	saveFileName = file.getName();
        	saveFilePath = file.getPath();
        	String text = readFromFile();
        	textPane.setText( text );
        	
        } else {
        	return null;
        }
         
        return openFileName;
	}


	public String save( String text ) throws SaveFileException {
		
		// new file. so ask user to select file name and save
		if( saveFilePath == null || saveFilePath.equals("") ) {
			
			int returnVal = fc.showSaveDialog(null);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
	            saveFileName = file.getName();
	            saveFilePath = file.getPath();
	            
	            writeToFile( text );
	            
	        } else {
	        	return null;
	        }
	        
		} else {
			// file already chosen. so just save
			 writeToFile( text );
		}
		
		return saveFileName;
	}
	
	
	
	
	public String saveAs( String text ) throws SaveFileException {
		saveFileName = null; 
		saveFilePath = null; 
		return save( text );
	}
	
	
	
	private String readFromFile() throws OpenFileException {
		
		String data = "";
		Charset charset = Charset.forName( GlobalConstants.CHARSET );
		Path path = FileSystems.getDefault().getPath( openFilePath, "" );
		
		/*try ( BufferedReader reader = Files.newBufferedReader( path, charset ) ) {
			String line = null;
			while ((line = reader.readLine()) != null) {
		        data += line + "\n";
		    }
			
		} catch( IOException e) {
		    System.err.format("Error while opening file: %s%n", e );
		    throw new OpenFileException( "Error while opening file", e);
		}
		*/
		
		try {
			List<String> lines = Files.readAllLines( path, charset );
			for( String line: lines ) {
				data += line + "\n";
			}
			
		} catch (IOException e) {
			System.err.format("Error while opening file: %s%n", e );
		    throw new OpenFileException( "Error while opening file", e);
		}
		
		return data;
	}
	
	
	
	private void writeToFile( String text ) throws SaveFileException {
		
		Charset charset = Charset.forName( GlobalConstants.CHARSET );
		Path path = FileSystems.getDefault().getPath( saveFilePath, "" );
		
		try ( BufferedWriter  writer = Files.newBufferedWriter( path, charset ) ) {
			writer.write( text, 0, text.length() );
			
		} catch( IOException e) {
		    System.err.format("Error while saving to file: %s%n", e );
		    throw new SaveFileException( "Error while saving file", e);
		}
	}

	
	
	class TextFileFilter extends FileFilter {

		@Override
		public boolean accept( File f ) {
			
			if (f.isDirectory()) {
	            return true;
	        }
			
			String fileName = f.getName();
			int index = fileName.lastIndexOf('.');
	        String extension = fileName.substring( index + 1 ).toLowerCase();
	        if( extension != null ) {
	            if( extension.equals( GlobalConstants.TEXT_FILE_EXT ) ) {
	            	return true;
	            } else {
	            	return false;
	            }
	        }
	        
	        return false;
		}

		@Override
		public String getDescription() {
			return GlobalConstants.TEXT_FILE_EXT_NAME;
		}
		
	}
}
