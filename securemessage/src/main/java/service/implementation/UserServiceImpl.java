package service.implementation;

import org.springframework.stereotype.Service;

import domain.User;
import dto.UserDto;
import dtoMapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import repository.UserRepository;
import service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;

    @Override
    public UserDto createUser(User user) { 
        return UserDtoMapper.fromUser(userRepository.create(user));
    }

}
