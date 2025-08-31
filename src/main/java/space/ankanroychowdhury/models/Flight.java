package space.ankanroychowdhury.models;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Getter
public final class Flight {
    private final String flightNo;
    private final String airline;
    private final String from;
    private final String to;
    private final int departDate;
    private final double departTime;
    private final double arrivalTime;
    private final Map<String, Fare> fares;

    private Flight(Builder b) {
        this.flightNo = b.flightNo;
        this.airline = b.airline;
        this.from = b.from;
        this.to = b.to;
        this.departDate = b.departDate;
        this.departTime = b.departTime;
        this.arrivalTime = b.arrivalTime;
        this.fares = new ConcurrentHashMap<>(b.fares);
    }

    public static class Builder {
        private String flightNo, airline, from, to; private int departDate; private double departTime; private double arrivalTime; private Map<String,Fare> fares = new HashMap<>();
        public Builder flightNo(String v){ this.flightNo = v; return this; }
        public Builder airline(String v){ this.airline = v; return this; }
        public Builder from(String v){ this.from = v; return this; }
        public Builder to(String v){ this.to = v; return this; }
        public Builder departDate(int v){ this.departDate = v; return this; }
        public Builder departTime(double v){ this.departTime = v; return this; }
        public Builder arrivalTime(double v){ this.arrivalTime = v; return this; }
        public Builder addFare(Fare fare){ this.fares.put(fare.getFareId(), fare); return this; }
        public Flight build(){ return new Flight(this); }
    }
}
