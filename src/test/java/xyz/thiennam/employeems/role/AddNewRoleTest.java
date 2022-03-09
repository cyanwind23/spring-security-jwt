package xyz.thiennam.employeems.role;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.thiennam.employeems.entity.Role;
import xyz.thiennam.employeems.entity.RoleNameEnum;
import xyz.thiennam.employeems.service.RoleService;

import java.util.Arrays;

@SpringBootTest
public class AddNewRoleTest {

    @Autowired
    RoleService roleService;

    @Test
    public void addNewRole() {
        Role role1 = new Role();
        role1.setRoleName(RoleNameEnum.ADMIN.getName());
        Role role2 = new Role();
        role2.setRoleName(RoleNameEnum.USER.getName());
        Role role3 = new Role();
        role3.setRoleName(RoleNameEnum.MANAGER.getName());

        for (Role role : Arrays.asList(role1, role2, role3)) {
            roleService.save(role);
        }
    }
}
