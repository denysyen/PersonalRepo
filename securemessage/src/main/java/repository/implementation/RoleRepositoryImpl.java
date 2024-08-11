package repository.implementation;

import java.util.Collection;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import RoleMapper.RoleRowMapper;
import domain.Role;
import enumeration.RoleType;
import exceptions.ApiExcpetion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import repository.RoleRepository;
import query.RoleQuery;

@Repository
@RequiredArgsConstructor
@Slf4j 
public class RoleRepositoryImpl implements RoleRepository <Role> {
    public final NamedParameterJdbcTemplate jdbc;


    @Override
    public Role create(Role data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public Role get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public Role update(Role data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role {} to user id: {}", roleName, userId);

        // save new user
        try { 
            Role role =  jdbc.queryForObject(RoleQuery.SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName", roleName), new RoleRowMapper());
            jdbc.update(RoleQuery.INSERT_ROLE_TO_USER, Map.of("userId", userId, "roleId", role.getId())); 

        } catch(EmptyResultDataAccessException exception) {
            throw new ApiExcpetion("No role found by name" + RoleType.ROLE_USER.name());

        } catch (Exception exception) {
            throw new ApiExcpetion("An error occurred. Please try again.");
        }
    }

    @Override
    public Role getRoleByUserId(Long userId, String roleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoleByUserId'");
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRoleByUserEmail'");
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUserRole'");
    }

}
