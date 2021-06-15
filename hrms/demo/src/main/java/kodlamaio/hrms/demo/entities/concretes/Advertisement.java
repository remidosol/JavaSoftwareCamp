package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.links.JobSeekerAdvertisementLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "advertisements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobSeekers"})
public class Advertisement implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of advertisement object")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Title is mandatory.")
    @ApiModelProperty(value = "title field of advertisement object")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Description is mandatory.")
    @ApiModelProperty(value = "description field of advertisement object")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(value = "salaryIntervalLeast field of advertisement object")
    @Column(name = "salary_interval_least")
    private int salaryIntervalLeast;

    @ApiModelProperty(value = "salaryIntervalMost field of advertisement object")
    @Column(name = "salary_interval_most")
    private int salaryIntervalMost;

    @NotNull(message = "Available Position Count is mandatory.")
    @ApiModelProperty(value = "availablePositionCount field of advertisement object")
    @Column(name = "available_position_count")
    private int availablePositionCount;

    @NotNull(message = "Last submit date is mandatory.")
    @Column(name = "last_submit_date")
    @ApiModelProperty(value = "lastSubmitDate field of advertisement object")
    private Date lastSubmitDate;

    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    @ApiModelProperty(value = "isActive field of advertisement object")
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(value = "createdAt field of advertisement object")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(value = "updatedAt field of advertisement object")
    private Date updatedAt;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "employer_id")
    @ApiModelProperty(value = "employer field of advertisement object")
    private Employer employer;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "job_position_id")
    @ApiModelProperty(value = "jobPosition field of advertisement object")
    private JobPosition jobPosition;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "city_id")
    @ApiModelProperty(value = "city field of advertisement object")
    private City city;

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<JobSeekerAdvertisementLink> jobSeekerLink;
}
