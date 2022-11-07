package food.app.exception;

public class InvalidOrderException extends Exception{
	
	public InvalidOrderException(String msg) {
		super("Unable to Process : " + msg);
	}
	
}
