package space.ankanroychowdhury.models;

import lombok.Getter;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public final class Fare {
    private final String fareId;
    private final double price;
    private final Set<Seat> availableSeats;

    private Fare (Builder b) {
        this.fareId = Objects.requireNonNull(b.fareId);
        this.price = b.price;
        this.availableSeats = ConcurrentHashMap.newKeySet();
        this.availableSeats.addAll(b.availableSeats);
    }

    public synchronized boolean areAllSeatsFree(Collection<Seat> seatsReq) {
        return availableSeats.containsAll(seatsReq);
    }

    public synchronized void reserveSeats(Collection<Seat> seatsReq) {
        if (!areAllSeatsFree(seatsReq)) {
            throw new RuntimeException("SEAT_NOT_AVAILABLE");
        }
        availableSeats.removeAll(seatsReq);
    }

    public synchronized void releaseSeats(Collection<Seat> seatsReq){
        availableSeats.addAll(seatsReq);
    }

    public static class Builder {
        private String fareId;
        private double price;
        private Set<Seat> availableSeats;

        public Builder fareId(String fareId) {
            this.fareId = fareId;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder availableSeats(Set<Seat> availableSeats) {
            this.availableSeats = availableSeats;
            return this;
        }

        public Fare build() {
            return new Fare(this);
        }
    }
}
