package xyz.thiennam.employeems.service.bean;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.thiennam.employeems.entity.Role;
import xyz.thiennam.employeems.repository.RoleRepository;
import xyz.thiennam.employeems.service.RoleService;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceBean implements RoleService {

    private final RoleRepository roleRepo;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }
}
