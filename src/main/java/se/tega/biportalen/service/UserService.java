package se.tega.biportalen.service;

import org.springframework.stereotype.Service;
import se.tega.biportalen.security.AppUser;
import se.tega.biportalen.security.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(AppUser user) {
        userRepository.save(user);
    }
}
