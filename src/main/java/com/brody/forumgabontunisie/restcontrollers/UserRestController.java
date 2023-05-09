package com.brody.forumgabontunisie.restcontrollers;

import com.brody.forumgabontunisie.dtos.FormModel;
import com.brody.forumgabontunisie.entities.User;
import com.brody.forumgabontunisie.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
@CrossOrigin(origins = "*")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<User> getAllUsers(){
        try{
            List<User> users = userService.findAllUsers();
            for(User u: users){
                u.setPassword(null);
            }
            return users;
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @PutMapping("/update")
    @ResponseBody
    public User updateUser(@RequestBody FormModel formModel){
        try{
            User user = userService.changePassword(formModel);
            user.setPassword(null);
            return user;
        }catch (Exception e){
            return null;
        }
    }

}
