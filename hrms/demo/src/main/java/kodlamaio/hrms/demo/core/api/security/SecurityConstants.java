package kodlamaio.hrms.demo.core.api.security;

public class SecurityConstants {

    public static final String[] ALLOWED_URLS = {"/api/auth/sign_up_as_job_seeker" , "/api/auth/sign_up_as_employer", "/login"};
    public static final String SECRET = "kodlamaio";
    public static final long EXPIRATION_TIME = 432_000_000; // 5 gün
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

}
