package xyz.thiennam.employeems.service;

import xyz.thiennam.employeems.entity.Role;

public interface RoleService {
    Role findByRoleName(String roleName);

    Role save(Role role);
}
