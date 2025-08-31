package space.ankanroychowdhury.validator;

public class DoubleValidator {
    public static boolean isDouble(final String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
