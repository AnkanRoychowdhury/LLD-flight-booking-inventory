package space.ankanroychowdhury.services;

import space.ankanroychowdhury.exceptions.UserNotFoundException;
import space.ankanroychowdhury.models.User;
import space.ankanroychowdhury.repository.UserRepository;

public final class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo){ this.repo = repo; }
    public User addUser(String id, String name, double funds){
        User u = new User.Builder().id(id).name(name).wallet(funds).build();
        repo.save(u); return u;
    }
    public User getOrThrow(String id){
        return repo.find(id).orElseThrow(() -> new UserNotFoundException("No associated User found with id: " + id));
    }
}
