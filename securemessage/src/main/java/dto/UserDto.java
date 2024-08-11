package dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private boolean enable;
    private boolean isNotLocked;
    private boolean isUsinfMfa;
    private LocalDateTime createAt;

}
