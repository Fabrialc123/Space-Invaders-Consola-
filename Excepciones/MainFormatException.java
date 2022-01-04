package Excepciones;

public class MainFormatException extends Exception {
	public MainFormatException() {
		super(); 
		}
	public MainFormatException(String message){
		super(message);
		}
	public MainFormatException(String message, Throwable cause){
	 super(message, cause);
	}
	public MainFormatException(Throwable cause){ 
		super(cause);
		}
	public MainFormatException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace){
	 super(message, cause, enableSuppression, writableStackTrace);
	}
}
