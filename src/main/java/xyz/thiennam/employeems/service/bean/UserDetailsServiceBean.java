package xyz.thiennam.employeems.service.bean;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.thiennam.employeems.entity.MyUserDetails;
import xyz.thiennam.employeems.entity.User;
import xyz.thiennam.employeems.repository.UserRepo;

@Service
@AllArgsConstructor
public class UserDetailsServiceBean implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found!"));
        return new MyUserDetails(user);
    }
}
