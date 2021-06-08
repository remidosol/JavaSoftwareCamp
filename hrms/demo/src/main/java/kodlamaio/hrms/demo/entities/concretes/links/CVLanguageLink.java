package kodlamaio.hrms.demo.entities.concretes.links;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.CurriculumVitae;
import kodlamaio.hrms.demo.entities.concretes.Language;
import kodlamaio.hrms.demo.utils.Enums;
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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "curriculumVitae" })
public class CVLanguageLink implements IEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Unique id field of CVLanguageLink object")
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cv_id")
    @ApiModelProperty(value = "CV field of CVLanguageLink object")
    private CurriculumVitae curriculumVitae;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "lang_id")
    @ApiModelProperty(value = "language field of CVLanguageLink object")
    private Language language;

    @NotNull
    @Column(name = "grade")
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Enumerated grade field of CVLanguageLink object")
    private Enums.LangGrade grade;
}
