package demo.service.impl.role;

public interface IRoleService <T,E>{
    T findByRoleName(E e);
}
