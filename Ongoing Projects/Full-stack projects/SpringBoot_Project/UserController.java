import org.srpingframework.web.bind.annotation.RequestMapping;
import org.srpingframework.web.bind.annotation.ResetController;

import com.example.UpdateUserDetailsRequesr;
import com.example.UserDetailsModelRequest;
import com.example.UserRest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.http.MediaType;


import  org.springframework.web.bind.annotation.GetMapping;
import  org.springframework.web.bind.annotation.Postapping;
import  org.springframework.web.bind.annotation.PutMapping;
import  org.springframework.web.bind.annotation.DeleteMapping;


@ResetController
@RequestMapping("/users") 
// http://localhost:8080/users
// when passing paremeters to function 
// one can use the required = false to 
// to run an excution  even without a parameter
// use if no defaultValue is provide to avoid NULL or
// undefined data

public class UserController {

    Map<String, UserRest> users;
    @Autowired
    UserService userService;
    
    @GetMapping  // to bind the method to an HTTP method (GET)
    public String getUsers(@RequestParam(value ="page", defaultValue="1") int page,
     @RequestParam(value ="limit", defaultValue="50", required = false ) int limit)  {
    
        return "get user was called with page =" + page + "and limit = " + limit;
    }
    @GetMapping(path = "/{userId}", 
      produces = {MediaType.APPLICATION_XML_VALUE   // here we demand media type of the outcome of the request
        , MediaType.APPLICATION_JSON_VALUE})  // to bind the method to an HTTP method (GET)   
    public  ResponseEntity<UserRest> getUser(@PathVariable String userId)  {
        UserRest retunValue = new UserRest(); // here we call an instance of UserRest class to access its methods
        retunValue.setEmail("test@test.com");
        retunValue.setFirsName("Xeros");
        retunValue.setLastName("2000");
        
       //  return "get user was called with userId =" + userId;
       return  new ResponseEntity<UserRest>(retunValue, HttpStatus.OK);
    }
    @Postapping(consumes = {MediaType.APPLICATION_XML_VALUE   // here we demand media type of the outcome of the request and consume
        , MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_XML_VALUE   // here we demand media type of the outcome of the request
            , MediaType.APPLICATION_JSON_VALUE}) 
    public ResponseEntity<UserRest> createUser(@RequesBody UserDetailsModelRequest userDetails) {
        UserRest retunValue = new UserRest();
        retunValue.setEmail(userDetails.getEmail());
        retunValue.setFirsName(userDetails.getFirstName());
        retunValue.setLastName(userDetails.getLastName());
        retunValue.setLastName(userDetails.getPassword());
        
        String userId = UUID.randomUUID().toString();
        returValue.setUserId(userId);
        
        if(users == null) users = new HashMap<>();
        users.put(userId, returValue);

        return  new ResponseEntity<UserRest>(retunValue, HttpStatus.OK);
    }
    @PutMapping(path= "/{userId}", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
    }, produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
    })
    /*
     *  We use @ to make reference towars a given class which
     *  will affect or define a parameter to this method. One
     *  can concatenate several class in a given parameter.The
     *  public particular classes of the project doesn't require
     *  an @ symbol.  
     */
    public UserRest updateUser(@PathVariable String userdId, @Valid @RequestBody UpdateUserDetailsRequesr userDetails) {
        UserRest storedUserDetails = user.get(userId); // easy way to declare objetc based in an instance of class in java (singleton)
        storedUserDetails.setFirsName(userDetails.getFirstName());
        storedUserDetails.setFirsName(userDetails.getLasttName());

        user.put(userId, storedUserDetails);

        return storedUserDetails;
    } 
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        users.remove(id);

        return ResponseEntity.noContent().build();
    }
    
}
