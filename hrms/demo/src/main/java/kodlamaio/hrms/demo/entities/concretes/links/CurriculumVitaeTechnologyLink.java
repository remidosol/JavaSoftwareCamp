package kodlamaio.hrms.demo.entities.concretes.links;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.enums.TechGradeEnums;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.demo.entities.concretes.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pivot_job_seeker_technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "curriculumVitae"})
public class CurriculumVitaeTechnologyLink implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of CurriculumVitaeTechnologyLink object")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id")
    private CurriculumVitae curriculumVitae;

    @ManyToOne
    @JoinColumn(name = "tech_id")
    private Technology technology;

    @Column(name = "grade")
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Enumerated grade field of CurriculumVitaeTechnologyLink object")
    private TechGradeEnums.TechGrade grade;
}