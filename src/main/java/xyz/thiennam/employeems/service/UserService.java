package xyz.thiennam.employeems.service;

import xyz.thiennam.employeems.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User save(User user);
    User addNewUser(User user);
    User findByUsername(String username);
    User findById(UUID id);
    // for admin panel
    List<User> getUsers();

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
