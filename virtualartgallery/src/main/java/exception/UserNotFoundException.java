package exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
        
    }

	@Override
	public String toString() {
		return "UserNotFoundException []";
	}
}



////   Main at line 170