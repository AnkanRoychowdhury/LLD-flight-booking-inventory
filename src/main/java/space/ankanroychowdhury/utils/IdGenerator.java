package space.ankanroychowdhury.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator { private final AtomicLong seq = new AtomicLong(1000);
    public String nextBooking(){ return "B"+seq.getAndIncrement(); }
}
