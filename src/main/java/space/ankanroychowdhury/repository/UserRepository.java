package space.ankanroychowdhury.repository;

import space.ankanroychowdhury.models.User;

import java.util.Optional;

public interface UserRepository {
    void save(User u);
    Optional<User> find(String id);
}