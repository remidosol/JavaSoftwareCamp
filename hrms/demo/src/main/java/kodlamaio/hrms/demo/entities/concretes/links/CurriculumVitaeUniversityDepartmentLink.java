package kodlamaio.hrms.demo.entities.concretes.links;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.demo.entities.concretes.Department;
import kodlamaio.hrms.demo.entities.concretes.University;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "pivot_job_seeker_education")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "curriculumVitae"})
public class CurriculumVitaeUniversityDepartmentLink {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of education object")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Starting date of education is mandatory.")
    @ApiModelProperty(value = "start field of education object")
    @Column(name = "start")
    private Date start;

    @ApiModelProperty(value = "end field of education object")
    @Column(name = "end")
    private Date end;

    @ApiModelProperty(value = "graduation_point field of education object")
    @Column(name = "graduation_point")
    private double graduationPoint;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    @ApiModelProperty(value = "CV field of education object")
    private CurriculumVitae curriculumVitae;

    @ManyToOne
    @JoinColumn(name = "university_id")
    @ApiModelProperty(value = "University field of education object")
    private University university;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ApiModelProperty(value = "Department field of education object")
    private Department department;
}
