package kodlamaio.hrms.demo.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kodlamaio.hrms.demo.business.abstracts.AuthService;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.enums.TokenNameEnums;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.Employer;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@Validated
@Api(value = "Auth Controller Docs")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        super();
        this.authService = authService;
    }

    @PostMapping("sign_up_as_job_seeker")
    @ApiOperation(value = "Sign up as a job seeker")
    public Result signUpAsJobSeeker(@Valid @RequestBody User user,
                                    @Valid @RequestBody JobSeeker jobSeeker) {
        return this.authService.signUpAsJobSeeker(user, jobSeeker);
    }

    @PostMapping("sign_up_as_employer")
    @ApiOperation(value = "Sign up as a employer")
    public Result signUpAsEmployer(@Valid @RequestBody User user,
                                   @Valid @RequestBody Employer employer) {
        return this.authService.signUpAsEmployer(user, employer);
    }

    @GetMapping("/verify_email")
    @ApiOperation(value = "Verify email")
    public Result verifyEmail(@RequestParam @Email(regexp = User.emailRegexp) String email,
                              @RequestParam String token) {
        return this.authService.verifyEmail(email, token);
    }

    @PostMapping("/forgot_password")
    @ApiOperation(value = "Send forgot password email")
    public Result forgotPassword(@RequestParam @Email(regexp = User.emailRegexp) String email) {
        return this.authService.forgotPasswordEmail(email);
    }

    @GetMapping("/reset_password")
    @ApiOperation(value = "Reset password")
    public Result resetPassword(@RequestParam @Email(regexp = User.emailRegexp) String email,
                                @RequestParam String token) {
        return this.authService.checkTokenAndEmail(email, token, TokenNameEnums.TokenName.FORGOT_PASSWORD);
    }

    @PostMapping("/reset_password")
    @ApiOperation(value = "Reset password")
    public Result resetPassword(@RequestParam String password,
                                @RequestParam @Email(regexp = User.emailRegexp) String email,
                                @RequestParam String token) {
        return this.authService.changePassword(email, password, token);
    }


}
