package org.warn.sinhala.texteditor.exceptions;


@SuppressWarnings("serial")
public class OpenFileException extends Exception {
	
	public OpenFileException() {
        super();
    }
	
	
	public OpenFileException(String message) {
        super(message);
    }
	
	public OpenFileException(String message, Throwable cause) {
        super(message, cause);
    }
	
	
	public OpenFileException(Throwable cause) {
        super(cause);
    }
	
}
