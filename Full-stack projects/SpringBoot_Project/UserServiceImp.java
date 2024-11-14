import java.util.HashMap;
import java.util.Map;

import org.jcp.xml.dsig.internal.dom.Utils;

import com.example.UserRest;

public class UserServiceImp {
    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImp() {}

    public UserServiceImp(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returValue = new UserRest();
        returValue.setEmail(userDetails.getEmail());
        returValue.setFirsName(userDetails.getFirs());
        returValue.setLastName(userDetails.getLastName());

        String userId = utils.generateUserId();
        returValue.setUserId(userId);

        if(user == null) users =  new HashMap<>();
        users.put(userId, returValue);

        return returValue;
    }
}
