package kodlamaio.hrms.demo.business.concretes;

import kodlamaio.hrms.demo.adapters.MernisServiceAdapter;
import kodlamaio.hrms.demo.business.abstracts.AuthService;
import kodlamaio.hrms.demo.business.abstracts.MernisService;
import kodlamaio.hrms.demo.core.dataAccess.UserDao;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.enums.RoleEnums;
import kodlamaio.hrms.demo.core.enums.TokenNameEnums;
import kodlamaio.hrms.demo.core.utilities.results.ErrorResult;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.core.utilities.results.SuccessResult;
import kodlamaio.hrms.demo.dataAccess.abstracts.ApiTokenDao;
import kodlamaio.hrms.demo.dataAccess.abstracts.RoleDao;
import kodlamaio.hrms.demo.entities.concretes.ApiToken;
import kodlamaio.hrms.demo.entities.concretes.Employer;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import kodlamaio.hrms.demo.utils.MernisSeviceUtils;
import kodlamaio.hrms.demo.utils.TemplateEngineUtils;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Service
public class AuthManager implements AuthService {

    private UserDao userDao;

    private JavaMailSender javaMailSender;

    private TemplateEngine templateEngine;

    private ApiTokenDao apiTokenDao;

    private RoleDao roleDao;

    @Autowired
    public AuthManager(UserDao userDao, JavaMailSender javaMailSender,
                       TemplateEngine templateEngine, ApiTokenDao apiTokenDao,
                       RoleDao roleDao) {
        this.userDao = userDao;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.apiTokenDao = apiTokenDao;
        this.roleDao = roleDao;
    }

    @Override
    public Result signUpAsJobSeeker(User user, JobSeeker jobSeeker) {

        MernisService mernisService = new MernisManager(new MernisServiceAdapter());

        Result result = MernisSeviceUtils.runMernisServices(new Result[]{
                mernisService.CheckIfJobSeekerIsRealPerson(jobSeeker),
                this.CheckIfYoungerAgeThan(jobSeeker, 18)
        });

        if (!result.isSuccess()) {
            System.out.println(result.getMessage());
            return new ErrorResult("Your id verification is unsuccessful according to your given info.");
        } else {
            user.setJobSeeker(jobSeeker);

            user.setRoles(Arrays.asList(roleDao.findByName(RoleEnums.UserRoles.ROLE_JOB_SEEKER.name())));

            this.sendVerifyMail(this.javaMailSender, this.templateEngine, user);

            this.userDao.save(user);

            return new SuccessResult("You are successfully signed up.");
        }

    }

    @Override
    public Result signUpAsEmployer(User user, Employer employer) {

        user.setEmployer(employer);

        user.setRoles(Arrays.asList(roleDao.findByName(RoleEnums.UserRoles.ROLE_EMPLOYER.name())));

        this.sendVerifyMail(this.javaMailSender, this.templateEngine, user);

        this.userDao.save(user);
        return new SuccessResult("You are successfully signed up.");
    }

    @Override
    public Result verifyEmail(String email, String token) {

        User user = this.userDao.findByEmail(email).get();

        List<ApiToken> apiTokens = user.getApiTokens();

        JobSeeker jobSeeker = user.getJobSeeker();
        Employer employer = user.getEmployer();

        boolean isJobSeeker = jobSeeker != null;
        boolean isEmployer = employer != null;

        boolean isAvailable = false;
        if (isJobSeeker){

            isAvailable = User.checkIsExpiredAndTokenType(apiTokens, token, TokenNameEnums.TokenName.EMAIL_CONFIRMATION);

            if (isAvailable){
                jobSeeker.setEmailConfirmed(true);
                user.setJobSeeker(jobSeeker);
                this.userDao.save(user);
                return new SuccessResult("Your email has been verified!");
            }
        } else if(isEmployer){
            isAvailable = User.checkIsExpiredAndTokenType(apiTokens, token, TokenNameEnums.TokenName.EMAIL_CONFIRMATION);
            if (isAvailable){
                employer.setEmailConfirmed(true);
                user.setEmployer(employer);
                this.userDao.save(user);
            }
        }

        return isAvailable ? new SuccessResult("Your email has been verified!") : new ErrorResult("Your verification token's expiry date has been expired.");
    }

    @Override
    public Result changePassword(String email, String password, String token) {

        try {
            User user = this.userDao.findByEmail(email).get();

            List<ApiToken> apiTokens = user.getApiTokens();

            boolean isAvailable = User.checkIsExpiredAndTokenType(apiTokens, token, TokenNameEnums.TokenName.FORGOT_PASSWORD);

            if (isAvailable) {
                user.setPassword(password);
                this.userDao.save(user);
            }

            return isAvailable ? new SuccessResult("Your password has been successfully changed!") : new ErrorResult("Your verification token's expiry date has been expired.");
        } catch (NoSuchElementException ex){

            return new ErrorResult("You email is not registered, please check!");
        }
    }

    // Sending forgot password email
    @Override
    public Result forgotPasswordEmail(String email) {

        try {

            User user = this.userDao.findByEmail(email).get();

            this.sendForgotPasswordMail(this.javaMailSender, this.templateEngine, user);

            return new SuccessResult("Forgot password email has been sent.");

        } catch (NoSuchElementException ex){

            return new ErrorResult("You email is not registered, please check!");
        }
    }

    @Override
    public Result checkTokenAndEmail(String email, String token, TokenNameEnums.TokenName tokenName) {

        try {

            User user = this.userDao.findByEmail(email).get();

            List<ApiToken> apiTokens = user.getApiTokens();

            boolean isAvailable = false;
            for (ApiToken apiToken : apiTokens){
                if (apiToken.getToken() == token && apiToken.getTokenName() == tokenName){
                    if((apiToken.getExpiryDate().getTime() - Calendar.getInstance().getTime().getTime()) <= 0){
                        isAvailable = false;
                    } else {
                        isAvailable = true;
                        break;
                    }
                }
            }
            return isAvailable ? new SuccessResult("You can change your password!") : new ErrorResult("Your verification token's expiry date has been expired.");

        } catch (NoSuchElementException ex){

            return new ErrorResult("You email is not registered, please check!");
        }
    }

    private Result CheckIfYoungerAgeThan(JobSeeker jobSeeker, int age) {
        LocalDate todayDateTime = LocalDate.now();
        LocalDate birthDate = jobSeeker.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int yearDifference = Period.between(birthDate, todayDateTime).getYears();
        if (yearDifference < age) {
            return new Result(false, "Person's age can't be smaller than " + age);
        }
        return new Result(true);
    }

    private void sendForgotPasswordMail(JavaMailSender jMS, TemplateEngine tE, User user){

        ApiToken apiToken = new ApiToken();
        apiToken.setExpiryDate(ApiToken.calculateExpiryDate(ApiToken.EXPIRATION));
        apiToken.setTokenName(TokenNameEnums.TokenName.FORGOT_PASSWORD);

        apiToken.setToken(RandomString.make(64));

        List<ApiToken> apiTokens = user.getApiTokens();
        if (apiTokens == null)
            apiTokens = new ArrayList<ApiToken>();

        apiTokens.add(apiToken);

        user.setApiTokens(apiTokens);

        MimeMessage forgotPasswordMessage = jMS.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(forgotPasswordMessage);
        TemplateEngineUtils templateEngineUtils = new TemplateEngineUtils(tE);

        try {
            helper.setTo(user.getEmail());
            helper.setSubject("Forgot Password | HRMS");
            helper.setText(templateEngineUtils.generateMailHtml("localhost", "8080", user.getEmail(), apiToken.getToken(), "forgotPassword"), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        this.javaMailSender.send(forgotPasswordMessage);
    }

    // Sending email verification email
    private void sendVerifyMail(JavaMailSender jMS, TemplateEngine tE, User user){

        ApiToken apiToken = new ApiToken();
        apiToken.setExpiryDate(ApiToken.calculateExpiryDate(ApiToken.EXPIRATION));
        apiToken.setTokenName(TokenNameEnums.TokenName.EMAIL_CONFIRMATION);

        String token = RandomString.make(64);

        apiToken.setToken(token);

        List<ApiToken> apiTokens = user.getApiTokens();
        if (apiTokens == null)
            apiTokens = new ArrayList<ApiToken>();

        apiTokens.add(apiToken);

        user.setApiTokens(apiTokens);

        MimeMessage verifyMessage = jMS.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(verifyMessage);
        TemplateEngineUtils templateEngineUtils = new TemplateEngineUtils(tE);

        try {
            helper.setTo(user.getEmail());
            helper.setSubject("Email Verification | HRMS");
            helper.setText(templateEngineUtils.generateMailHtml("localhost", "8080", user.getEmail(), apiToken.getToken(), "verifyEmail"), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        this.javaMailSender.send(verifyMessage);
    }

}
