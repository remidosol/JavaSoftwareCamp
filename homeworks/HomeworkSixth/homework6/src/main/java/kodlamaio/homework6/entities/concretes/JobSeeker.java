package kodlamaio.homework6.entities.concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

import kodlamaio.homework6.entities.abstracts.IEntity;

@Entity
@Table(name = "job_seekers")
@Getter @Setter @NoArgsConstructor
public class JobSeeker implements IEntity, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "first_name")
    @Size(min = 2)
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(min = 2)
    private String lastName;

    @NotNull
    @Column(name = "national_id")
    @Size(min = 11, max = 11)
    private String nationalId;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}