package kodlamaio.hrms.demo.business.abstracts;

import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.enums.TokenNameEnums;
import kodlamaio.hrms.demo.core.utilities.results.Result;
import kodlamaio.hrms.demo.entities.concretes.Employer;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;

public interface AuthService {

    Result signUpAsJobSeeker(User user, JobSeeker jobSeeker);

    Result signUpAsEmployer(User user, Employer employer);

    Result forgotPasswordEmail(String email);

    Result verifyEmail(String email, String token);

    Result changePassword(String email, String password, String token);

    Result checkTokenAndEmail(String email, String token, TokenNameEnums.TokenName tokenName);
}
