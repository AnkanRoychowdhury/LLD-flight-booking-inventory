package space.ankanroychowdhury.exceptions;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException() {
        super("Invalid command. Please check the command and try again.");
    }

    public InvalidCommandException(String message) {
        super(message);
    }
}
