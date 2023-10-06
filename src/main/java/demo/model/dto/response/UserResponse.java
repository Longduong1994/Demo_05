package demo.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    @JsonIgnore
    private String username;
    private String password;
    @JsonIgnore
    private String email;
    private boolean status;
    private Set<Role> roles = new HashSet<>();
}
