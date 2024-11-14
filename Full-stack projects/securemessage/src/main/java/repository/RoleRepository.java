package repository;

import java.util.*;

import domain.Role;

public interface RoleRepository<T extends Role> {

    // Basic CRUD Operations //
    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);
    void addRoleToUser(Long id, String name);

    // custom operations // 
    Role getRoleByUserId(Long userId, String roleName);
    Role getRoleByUserEmail(String email);
    void updateUserRole(Long userId, String roleName);

}
