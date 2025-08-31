package space.ankanroychowdhury.models;

import java.util.*;

public final class Booking {
    private final String id;
    private final String userId;
    private final String flightKey; // flightNo_date
    private final String fareId;
    private final List<Seat> seats;
    private final double amount;
    private BookingStatus status;
    private Booking(Builder b){
        this.id = b.id; this.userId = b.userId; this.flightKey = b.flightKey; this.fareId = b.fareId;
        this.seats = List.copyOf(b.seats); this.amount = b.amount; this.status = BookingStatus.CONFIRMED;
    }
    public String id(){ return id; }
    public String userId(){ return userId; }
    public String flightKey(){ return flightKey; }
    public String fareId(){ return fareId; }
    public List<Seat> seats(){ return seats; }
    public double amount(){ return amount; }
    public BookingStatus status(){ return status; }
    public void cancel(){ this.status = BookingStatus.CANCELLED; }
    public static class Builder {
        private String id, userId, flightKey, fareId; private List<Seat> seats = new ArrayList<>(); private double amount;
        public Builder id(String v){ this.id=v; return this; }
        public Builder userId(String v){ this.userId=v; return this; }
        public Builder flightKey(String v){ this.flightKey=v; return this; }
        public Builder fareId(String v){ this.fareId=v; return this; }
        public Builder seats(Collection<Seat> c){ this.seats = new ArrayList<>(c); return this; }
        public Builder amount(double v){ this.amount=v; return this; }
        public Booking build(){ return new Booking(this); }
    }
}
