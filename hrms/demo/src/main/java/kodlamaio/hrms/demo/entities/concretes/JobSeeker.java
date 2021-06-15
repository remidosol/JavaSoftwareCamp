package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.links.JobSeekerAdvertisementLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "job_seekers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
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
    @Column(name = "national_id", unique = true)
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

    @Column(name = "avatar_url")
    @ApiModelProperty(value = "Avatar URL field of job seeker object")
    private String avatarUrl;

    @Column(name = "is_email_confirmed", nullable = false, columnDefinition = "boolean default false")
    @ApiModelProperty(value = "isEmailConfirmed field of job seeker object")
    private boolean isEmailConfirmed;

    @Column(name = "is_confirmed_with_mernis", nullable = false, columnDefinition = "boolean default false")
    @ApiModelProperty(value = "isConfirmedWithMernis field of job seeker object")
    private boolean isConfirmedWithMernis;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ApiModelProperty(value = "user field of job seeker object")
    private User user;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobSeekerAdvertisementLink> advertisementLink;

    @OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private CurriculumVitae curriculumVitae;
}