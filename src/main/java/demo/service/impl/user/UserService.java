package demo.service.impl.user;

import demo.exception.RegisterException;
import demo.model.dto.request.RegisterForm;
import demo.model.dto.response.UserResponse;

import demo.model.entity.User;
import demo.repository.IUserRepository;

import demo.service.mapper.user.IUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService implements IUserService<RegisterForm, UserResponse, User>{
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public UserResponse save(RegisterForm registerForm) throws RegisterException {
        if (userRepository.existsByUsername(registerForm.getUsername())){
            throw new RegisterException("User already exists");
        }
        if (userRepository.existsByEmail(registerForm.getEmail())){
            throw new RegisterException("Email already exists");
        }
        if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())){
            throw new RegisterException("Password was wrong ");
        }
        return userMapper.toResponse(userRepository.save(userMapper.toEntity(registerForm)));
    }
}
