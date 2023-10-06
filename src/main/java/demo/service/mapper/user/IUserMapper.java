package demo.service.mapper.user;

import demo.model.dto.request.RegisterForm;
import demo.model.dto.response.UserResponse;
import demo.model.entity.User;
import demo.service.mapper.IGenericMapper;

public interface IUserMapper extends IGenericMapper<User, RegisterForm, UserResponse> {
}
