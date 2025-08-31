package space.ankanroychowdhury.exceptions;

public class InvalidAmountException extends UserFundsException {
    public InvalidAmountException() {
        super("Amount should be greater than zero");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}
