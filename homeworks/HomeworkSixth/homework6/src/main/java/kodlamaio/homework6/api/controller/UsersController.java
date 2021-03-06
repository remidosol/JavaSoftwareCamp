package kodlamaio.homework6.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.homework6.business.abstracts.UserService;
import kodlamaio.homework6.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Api(value = "Homework API Docs | Users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }


    @GetMapping(value = "/")
    @ApiOperation(value = "Fetch all users")
    public List<User> fetch(){
        return this.userService.fetch();
    }


    @GetMapping(value = "/find_by_id")
    @ApiOperation(value = "Find user by ID")
    public @ResponseBody Optional<User> findById(@RequestParam(required = true) String id){
        return this.userService.findById(Long.parseLong(id));
    }


    @GetMapping(value = "/find_by_email")
    @ApiOperation(value = "Find user by Email")
    public @ResponseBody Optional<User> findByEmail(@RequestParam(required = true) String email){
        return this.userService.findByEmail(email);
    }

}
