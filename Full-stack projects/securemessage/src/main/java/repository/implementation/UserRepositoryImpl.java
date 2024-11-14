package repository.implementation;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import domain.Role;
import domain.User;
import exceptions.ApiExcpetion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import repository.RoleRepository;
import repository.UserRepository;
import query.UserQuery;
import enumeration.RoleType;
import enumeration.VerificationType;

@Repository
@RequiredArgsConstructor
@Slf4j 
public class UserRepositoryImpl implements UserRepository<User> {
    // to connect with the DB via query syntax
    public final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role>  roleRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    public User create(User user) {
        // check if the email is unique
        if(getEmailCount(user.getEmail().trim().toLowerCase()) > 0) throw new ApiExcpetion("Email laready in use. Please use another email");
        // save new user
        try { 
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource paremeters = getSqlParameterSource(user);
            jdbc.update(UserQuery.INSERT_USER_QUERY, paremeters, holder);
            user.setId((holder.getKey().longValue()));
            // add role to the user
            roleRepository.addRoleToUser(user.getId(), RoleType.ROLE_USER.name());
            // send verification URL 
            String verificationUrl = getVerficationUrl(UUID.randomUUID().toString(), VerificationType.ACCOUNT.getType());
            // save url in verification table 
            jdbc.update(UserQuery.INSERT_ACCOUNT_VERIFICATION_URL_QUERY, Map.of("userId", user.getId(), "url", verificationUrl));
            //send email to user with the verification URL
          // -- emailService.sendVerificationUrl(user.getFirstName(), user.getEmail(), verificationUrl, VerificationType.ACCOUNT.getType());
            user.setEnable(false);
            user.setIsNotLocked(true);
            return user;
        } catch(EmptyResultDataAccessException exception) {
            throw new ApiExcpetion("No role found by name" + RoleType.ROLE_USER.name());

        } catch (Exception exception) {
            throw new ApiExcpetion("An error occurred. Please try again.");
        }

    }


    @Override
    public Collection list(int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'list'");
    }

    @Override
    public User get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public User update(User data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Integer getEmailCount(String email) {
       return jdbc.queryForObject(UserQuery.COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }
     
    private String getVerficationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/"+ type +"/"+ key).toUriString();
        

    }

}
