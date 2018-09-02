package org.warn.sinhala.texteditor.exceptions;


@SuppressWarnings("serial")
public class SaveFileException extends Exception {
	
	
	public SaveFileException() {
        super();
    }
	
	
	public SaveFileException(String message) {
        super(message);
    }
	
	public SaveFileException(String message, Throwable cause) {
        super(message, cause);
    }
	
	
	public SaveFileException(Throwable cause) {
        super(cause);
    }
	
}
