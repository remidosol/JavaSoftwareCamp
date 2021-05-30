package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(UserListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "jobSeeker", "employer", "staff" })
public class User implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of user object")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Email
    @ApiModelProperty(value = "email field of user object")
    @Column(name = "email")
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
    private Set<JobPosition> jobPositions = new HashSet<>();
}

class UserListener {

    @PrePersist
    @PreUpdate
    public void hashPassword(User user){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
    }

}