package jsp.spring_boot.exceptions;

public class IdNotFoundException extends RuntimeException {

	public IdNotFoundException(String message) {
		super(message);
	}
	
}
