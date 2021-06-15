package kodlamaio.hrms.demo.core.api.security;


import io.jsonwebtoken.Jwts;
import kodlamaio.hrms.demo.core.dataAccess.UserDao;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.enums.TokenNameEnums;
import kodlamaio.hrms.demo.dataAccess.abstracts.ApiTokenDao;
import kodlamaio.hrms.demo.entities.concretes.ApiToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static kodlamaio.hrms.demo.core.api.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApiTokenDao apiTokenDao;

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
            chain.doFilter(req, res);
            return;
        } else if (notAuthenticated(authentication)){
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP 401.
            chain.doFilter(req, res);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            String parsedToken = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (parsedToken != null) {
                return new UsernamePasswordAuthenticationToken(parsedToken, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }


    private boolean notAuthenticated(UsernamePasswordAuthenticationToken parsedToken) {

        ApiToken apiToken = this.apiTokenDao.getByToken(parsedToken.getPrincipal().toString());
        // compare the token with what you have in your database...or in-memory...or in LDAP...
        if (apiToken != null){
            User user = apiToken.getUser();
            List<ApiToken> apiTokens = user.getApiTokens();
            return User.checkIsExpiredAndTokenType(apiTokens, parsedToken.getPrincipal().toString(), TokenNameEnums.TokenName.JWT_AUTH_TOKEN);
        } else {
            return false;
        }
    }
}
