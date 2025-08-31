package space.ankanroychowdhury.repository;

import space.ankanroychowdhury.models.Flight;
import java.util.*;

public interface FlightRepository {
    void save(Flight f);
    Optional<Flight> findByKey(String key);
    List<Flight> search(String from, String to, int date);
}
