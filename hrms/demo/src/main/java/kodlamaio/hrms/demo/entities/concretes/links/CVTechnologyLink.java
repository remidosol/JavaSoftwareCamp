package kodlamaio.hrms.demo.entities.concretes.links;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.demo.entities.concretes.Technology;
import kodlamaio.hrms.demo.utils.Enums;
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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "curriculumVitae" })
public class CVTechnologyLink implements IEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of CVTechnologyLink object")
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
    @ApiModelProperty(value = "Enumerated grade field of CVTechnologyLink object")
    private Enums.TechGrade grade;
}