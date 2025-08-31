package space.ankanroychowdhury.models;
import java.util.Locale;

public record Seat(String number) {
    public Seat(String number) {
        this.number = number.toLowerCase(Locale.ROOT);
    }
}
