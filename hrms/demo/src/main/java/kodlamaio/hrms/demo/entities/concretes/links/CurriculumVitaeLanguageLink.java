package kodlamaio.hrms.demo.entities.concretes.links;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.enums.LangGradeEnums;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.demo.entities.concretes.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "pivot_job_seeker_languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "curriculumVitae"})
public class CurriculumVitaeLanguageLink implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of CurriculumVitaeLanguageLink object")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cv_id")
    @ApiModelProperty(value = "CV field of CurriculumVitaeLanguageLink object")
    private CurriculumVitae curriculumVitae;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "lang_id")
    @ApiModelProperty(value = "language field of CurriculumVitaeLanguageLink object")
    private Language language;

    @NotNull
    @Column(name = "grade")
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Enumerated grade field of CurriculumVitaeLanguageLink object")
    private LangGradeEnums.LangGrade grade;
}
