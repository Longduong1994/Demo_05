package demo.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterForm {
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
    private Set<String> roles;
}
