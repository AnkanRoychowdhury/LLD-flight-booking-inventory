package space.ankanroychowdhury.repository;

import space.ankanroychowdhury.models.Booking;
import java.util.*;

public interface BookingRepository {
    void save(Booking b);
    Optional<Booking> find(String id);
    List<Booking> findByUser(String userId);
}
