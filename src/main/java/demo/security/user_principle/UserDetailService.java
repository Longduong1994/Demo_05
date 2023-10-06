package demo.security.user_principle;

import demo.model.entity.User;
import demo.repository.IUserRepository;
import demo.service.impl.user.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = (User) userService.findByUserName(username).orElseThrow(()-> new UsernameNotFoundException("Username Not Found"));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return UserPrinciple.build(user);
    }
}
