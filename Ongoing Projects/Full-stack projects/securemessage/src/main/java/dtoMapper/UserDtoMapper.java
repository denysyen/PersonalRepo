package dtoMapper;

import org.springframework.beans.BeanUtils;

import domain.User;
import dto.UserDto;

public class UserDtoMapper {
    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

}
