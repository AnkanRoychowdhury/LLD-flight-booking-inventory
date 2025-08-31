package space.ankanroychowdhury.exceptions;

import javax.lang.model.UnknownEntityException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
