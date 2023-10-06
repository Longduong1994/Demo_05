package demo.service.impl.role;

import demo.model.entity.Role;
import demo.model.entity.RoleName;
import demo.repository.IRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService implements IRoleService<Role, RoleName>{
    public final IRoleRepository roleRepository;
    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(()->new RuntimeException("Role not found"));
    }
}
