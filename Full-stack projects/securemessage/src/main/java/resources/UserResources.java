package resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.*;
import domain.HttpResponse;
import domain.User;
import dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import static java.time.LocalDateTime.now;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResources {
    private final UserService userService;
    // @Valid annotation makes sure that we get an exception send to us if any
    // of the field of the target class (UserDto) was given empty or with any error
    @PostMapping("/register")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user){
        UserDto userDto = userService.createUser(user);
        return ResponseEntity.created(null).body(
            HttpResponse.builder()
                     .timeStamp(now().toString())
                     .data(Map.of("user",userDto))
                     .message("User created")
                     .status(HttpStatus.CREATED)
                     .statusCode(HttpStatus.CREATED.value())
                     .build());

    }

    private URI getUri() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }
    

}
