package kodlamaio.homework6.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import kodlamaio.homework6.entities.abstracts.IEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
@EntityListeners(UserListener.class)
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private JobSeeker jobSeeker;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employer employer;
}

class UserListener {

    @PrePersist
    @PreUpdate
    public void hashPassword(User user){
        Argon2PasswordEncoder encoder = new Argon2PasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
    }

}