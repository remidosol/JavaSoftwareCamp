package kodlamaio.hrms.demo.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.Employer;
import kodlamaio.hrms.demo.entities.concretes.JobPosition;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import kodlamaio.hrms.demo.entities.concretes.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(UserListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "jobSeeker", "employer", "staff" })
public class User implements IEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of user object")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Email
    @Pattern(message = "Please type a valid email.", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])\n")
    @ApiModelProperty(value = "email field of user object")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @ApiModelProperty(value = "password field of user object")
    @Column(name = "password")
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

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private JobSeeker jobSeeker;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employer employer;

    @OneToOne(mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Staff staff;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "users")
    private Set<JobPosition> jobPositions;
}

class UserListener {

    @PrePersist
    @PreUpdate
    public void hashPassword(User user){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
    }

}