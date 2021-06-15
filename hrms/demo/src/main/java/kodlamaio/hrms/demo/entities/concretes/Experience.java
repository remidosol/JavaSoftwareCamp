package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "experiences")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "curriculumVitae"})
public class Experience implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of experience object")
    @Column(name = "id")
    private String id;

    @NotNull(message = "Company Name is mandatory.")
    @ApiModelProperty(value = "company_name field of experience object")
    @Column(name = "company_name")
    private String companyName;

    @NotNull(message = "Description is mandatory.")
    @ApiModelProperty(value = "description field of experience object")
    @Column(name = "description")
    private String description;

    @ApiModelProperty(value = "salaryIntervalLeast field of experience object")
    @Column(name = "salary_interval_least")
    private int salaryIntervalLeast;

    @ApiModelProperty(value = "salaryIntervalMost field of experience object")
    @Column(name = "salary_interval_most")
    private int salaryIntervalMost;

    @NotNull(message = "Starting date of job is mandatory.")
    @Column(name = "start")
    @ApiModelProperty(value = "start field of experience object")
    private Date start;

    @Column(name = "end")
    @ApiModelProperty(value = "end field of experience object")
    private Date end;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(value = "createdAt field of experience object")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(value = "updatedAt field of experience object")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CurriculumVitae curriculumVitae;

    @ManyToOne()
    @MapsId
    @JoinColumn(name = "city_id")
    @ApiModelProperty(value = "city field of experience object")
    private City city;
}
