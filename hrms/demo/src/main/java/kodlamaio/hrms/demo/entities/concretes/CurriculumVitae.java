package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeLanguageLink;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeTechnologyLink;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeUniversityDepartmentLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "curriculum_vitaes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "jobSeeker"})
public class CurriculumVitae implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of curriculum vitae object")
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "cover letter field of curriculum vitae object")
    @Column(name = "cover_letter")
    private String coverLetter;

    @ApiModelProperty(value = "GitHub url field of curriculum vitae object")
    @Column(name = "github_url")
    private String githubUrl;

    @ApiModelProperty(value = "LinkedIn url field of curriculum vitae object")
    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(value = "createdAt field of curriculum vitae object")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(value = "updatedAt field of curriculum vitae object")
    private Date updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "job_seeker_id")
    @ApiModelProperty(value = "jobSeeker field of curriculum vitae object")
    private JobSeeker jobSeeker;


    @OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CurriculumVitaeUniversityDepartmentLink> educations;

    @OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CurriculumVitaeTechnologyLink> cvTechs;

    @OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CurriculumVitaeLanguageLink> cvLangs;

    @OneToMany(mappedBy = "curriculumVitae", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Experience> experiences;
}
