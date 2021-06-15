package kodlamaio.hrms.demo.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.enums.TokenNameEnums;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.*;
import kodlamaio.hrms.demo.entities.concretes.links.UserJobPositionLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(UserListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobSeeker", "employer", "staff"})
public class User implements IEntity, Serializable {
    public static final String emailRegexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\n";

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of user object")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Email is mandatory.")
    @NotEmpty(message = "Email is mandatory.")
    @NotBlank(message = "Email is mandatory.")
    @Email
    @Pattern(message = "Please type a valid email.", regexp = emailRegexp)
    @ApiModelProperty(value = "email field of user object")
    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @NotNull(message = "Password is mandatory.")
    @NotEmpty(message = "Password is mandatory.")
    @NotBlank(message = "Password is mandatory.")
    @ApiModelProperty(value = "password field of user object")
    @Column(name = "password", nullable = false)
    @Size(min = 8, max = 16)
    private String password;

    @CreationTimestamp
    @ApiModelProperty(value = "createdAt field of user object")
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @ApiModelProperty(value = "updatedAt field of user object")
    @Column(name = "updated_at")
    private Date updatedAt;

//    @NotNull
//    @Column(name = "role")
//    @Enumerated(EnumType.ORDINAL)
//    @ApiModelProperty(value = "Enumerated role field of user object")
//    private RoleEnums.UserRoles role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private JobSeeker jobSeeker;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Employer employer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Staff staff;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private UserJobPositionLink jobPositionLink;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApiToken> apiTokens;

    @ManyToMany
    @JoinTable(
            name = "pivot_users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;


    public static boolean checkIsExpiredAndTokenType(List<ApiToken> apiTokens, String token, TokenNameEnums.TokenName tokenName) {

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
        return isAvailable;
    }
}

@NoArgsConstructor
class UserListener {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserListener(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PrePersist
    @PreUpdate
    public void hashPassword(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
    }

//    @PostPersist
//    public void sendVerificationEmail(JavaMailSender javaMailSender, TemplateEngine templateEngine, User user, ApiToken apiToken){
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        TemplateEngineUtils templateEngineUtils = new TemplateEngineUtils(templateEngine);
//
//        try {
//            helper.setTo(user.getEmail());
//            helper.setSubject("Email Verification | HRMS");
//            helper.setText(templateEngineUtils.generateMailHtml("localhost", "8080", user.getEmail(), apiToken.getToken()), true);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        javaMailSender.send(message);
//    }

}