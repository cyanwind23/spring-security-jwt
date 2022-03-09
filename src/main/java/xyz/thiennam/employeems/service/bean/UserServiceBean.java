package xyz.thiennam.employeems.service.bean;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.thiennam.employeems.entity.Role;
import xyz.thiennam.employeems.entity.RoleNameEnum;
import xyz.thiennam.employeems.entity.User;
import xyz.thiennam.employeems.repository.UserRepo;
import xyz.thiennam.employeems.service.RoleService;
import xyz.thiennam.employeems.service.UserService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceBean implements UserService {

    private final UserRepo userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    public User addNewUser(User user) {
        Role roleUser = roleService.findByRoleName(RoleNameEnum.USER.getName());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null) {
            user.setRoles(new LinkedHashSet<>());
        }
        user.getRoles().add(roleUser);
        user.setActive(false);

        return userRepo.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
