package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import kodlamaio.hrms.demo.entities.abstracts.IEntity;

@Entity
@Table(name = "job_seekers")
@Data
@NoArgsConstructor @AllArgsConstructor
public class JobSeeker implements IEntity, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @ApiModelProperty(value = "Unique id field of job seeker object")
    private Long id;

    @Column(name = "first_name")
    @Size(min = 2, message = "Your firstname must be grater than 2 chars.")
    @NotNull(message = "Firstname is mandatory.")
    @NotEmpty(message = "Firstname is mandatory.")
    @NotBlank(message = "Firstname is mandatory.")
    @ApiModelProperty(value = "firstName field of job seeker object")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(min = 2, message = "Your lastname must be grater than 2 chars.")
    @NotNull(message = "Lastname is mandatory.")
    @NotEmpty(message = "Lastname is mandatory.")
    @NotBlank(message = "Lastname is mandatory.")
    @ApiModelProperty(value = "lastName field of job seeker object")
    private String lastName;

    @NotNull
    @Column(name = "national_id")
    @Size(min = 11, max = 11)
    @NotNull(message = "National ID is mandatory.")
    @NotEmpty(message = "National ID is mandatory.")
    @NotBlank(message = "National ID is mandatory.")
    @ApiModelProperty(value = "Unique nationalId field of job seeker object")
    private String nationalId;

    @NotNull
    @Column(name = "date_of_birth")
    @NotNull(message = "Date of Birth is mandatory.")
    @NotEmpty(message = "Date of Birth is mandatory.")
    @NotBlank(message = "Date of Birth is mandatory.")
    @ApiModelProperty(value = "dateOfBirth field of job seeker object")
    private Date dateOfBirth;

    @Column(name = "is_email_confirmed", nullable = false, columnDefinition = "boolean default false")
    @ApiModelProperty(value = "isEmailConfirmed field of job seeker object")
    private boolean isEmailConfirmed;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    @ApiModelProperty(value = "isActive field of job seeker object")
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(value = "createdAt field of job seeker object")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(value = "updatedAt field of job seeker object")
    private Date updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "user field of job seeker object")
    private User user;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "pivot_job_seeker_advertisements",
            joinColumns =
                    { @JoinColumn(name = "job_seeker_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "advertisement_id", referencedColumnName = "id") })
    private Set<Advertisement> appliedAdvertisements = new HashSet<Advertisement>(0);
}