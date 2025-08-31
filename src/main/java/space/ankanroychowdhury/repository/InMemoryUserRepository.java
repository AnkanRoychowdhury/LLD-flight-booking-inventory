package space.ankanroychowdhury.repository;

import space.ankanroychowdhury.models.User;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> store = new ConcurrentHashMap<>();
    public void save(User u){ store.put(u.getId(), u); }
    public Optional<User> find(String id){ return Optional.ofNullable(store.get(id)); }
}
