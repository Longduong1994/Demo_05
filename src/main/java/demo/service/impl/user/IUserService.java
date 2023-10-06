package demo.service.impl.user;

import demo.exception.RegisterException;

import java.util.Optional;

public interface IUserService <T,V,E>{
    Optional<E> findByUserName(String username);
    V save(T t) throws RegisterException;
}
