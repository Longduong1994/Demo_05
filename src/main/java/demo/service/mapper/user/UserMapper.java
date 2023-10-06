package demo.service.mapper.user;

import demo.model.dto.request.RegisterForm;
import demo.model.dto.response.UserResponse;
import demo.model.entity.Role;
import demo.model.entity.RoleName;
import demo.model.entity.User;
import demo.service.impl.role.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
@AllArgsConstructor
public class UserMapper implements IUserMapper{
    private final IRoleService<Role, RoleName> roleService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User toEntity(RegisterForm registerForm) {
        Set<Role> roles = new HashSet<>();
        if (registerForm.getRoles() == null || registerForm.getRoles().isEmpty()) {
            roles.add(roleService.findByRoleName(RoleName.ROLE_USER));
        }
        return User.builder()
                .username(registerForm.getUsername())
                .email(registerForm.getEmail())
                .password(passwordEncoder.encode(registerForm.getPassword()))
                .roles(roles)
                .status(true).build();
    }

    @Override
    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .status(user.isStatus())
                .build();
    }
}
