package kodlamaio.hrms.demo.core.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kodlamaio.hrms.demo.core.dataAccess.UserDao;
import kodlamaio.hrms.demo.core.enums.TokenNameEnums;
import kodlamaio.hrms.demo.entities.concretes.ApiToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static kodlamaio.hrms.demo.core.api.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao userDao;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            kodlamaio.hrms.demo.core.entities.User credentials = new ObjectMapper()
                    .readValue(req.getInputStream(), kodlamaio.hrms.demo.core.entities.User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getEmail(),
                            credentials.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();


        String parsedToken = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        kodlamaio.hrms.demo.core.entities.User user = this.userDao.findByEmail(((User) auth.getPrincipal()).getUsername()).get();

        List<ApiToken> apiTokens = user.getApiTokens();
        if (apiTokens == null)
            apiTokens = new ArrayList<>();

        ApiToken apiToken = new ApiToken();
        apiToken.setTokenName(TokenNameEnums.TokenName.JWT_AUTH_TOKEN);
        apiToken.setToken(parsedToken);
        apiToken.setExpiryDate((java.sql.Date) new Date(System.currentTimeMillis() + EXPIRATION_TIME));

        apiTokens.add(apiToken);

        user.setApiTokens(apiTokens);

        this.userDao.save(user);

        // You should get the Bearer token from response header in React
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}