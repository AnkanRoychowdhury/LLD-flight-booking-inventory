package space.ankanroychowdhury.exceptions;

public class UserFundsException extends IllegalArgumentException {
    public UserFundsException() {
        super("Please check the funds in the wallet");
    }

    public UserFundsException(String message) {
        super(message);
    }
}
