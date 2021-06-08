package kodlamaio.hrms.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.demo.business.abstracts.UserService;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Api(value = "User Controller Docs")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }


    @GetMapping(value = "/")
    @ApiOperation(value = "Fetch all users")
    public DataResult<List<User>> fetch(){
        return this.userService.fetch();
    }


    @GetMapping(value = "/find_by_id")
    @ApiOperation(value = "Find user by ID")
    public @ResponseBody Optional<User> findById(@Valid @RequestParam String id){
        return this.userService.findById(Long.parseLong(id));
    }


    @GetMapping(value = "/find_by_email")
    @ApiOperation(value = "Find user by Email")
    public @ResponseBody Optional<User> findByEmail(@Valid @RequestParam String email){
        return this.userService.findByEmail(email);
    }

}
