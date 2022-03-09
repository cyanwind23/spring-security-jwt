package xyz.thiennam.employeems.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.thiennam.employeems.entity.GenderEnum;
import xyz.thiennam.employeems.entity.RoleNameEnum;
import xyz.thiennam.employeems.entity.User;
import xyz.thiennam.employeems.service.RoleService;
import xyz.thiennam.employeems.service.UserService;

import java.time.LocalDate;
import java.util.LinkedHashSet;

@SpringBootTest
public class UserTest {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Test
    public void addAdmin() {
        User admin = new User();

        admin.setUsername("thiennam23");
        admin.setPassword("thiennam23");
        admin.setEmail("thiennam23@gmail.com");
        admin.setDob(LocalDate.of(2001, 3, 23));
        admin.setRoles(new LinkedHashSet<>());
        admin.getRoles().add(roleService.findByRoleName(RoleNameEnum.ADMIN.getName()));
        admin.setFirstName("Nam");
        admin.setLastName("Thien");
        admin.setGender(GenderEnum.MALE.getName());

        userService.addNewUser(admin);

    }
}
