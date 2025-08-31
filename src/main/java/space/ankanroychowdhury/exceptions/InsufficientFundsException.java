package space.ankanroychowdhury.exceptions;

public class InsufficientFundsException extends UserFundsException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
