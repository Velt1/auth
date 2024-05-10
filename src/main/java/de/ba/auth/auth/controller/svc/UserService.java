package de.ba.auth.auth.controller.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.ba.auth.auth.model.User;
import de.ba.auth.auth.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
        // Implement your logic to fetch a user by username
        // Return null or throw an exception if the user is not found
    }

    public User saveUser(User user) {
        return userRepo.save(user);
        // Save and return the user
    }

    // Implement other methods as needed
}
