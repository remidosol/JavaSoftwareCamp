package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeTechnologyLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "cvTechnology"})
public class Technology implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of technology object")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Technology/Skill is mandatory.")
    @ApiModelProperty(value = "tech_name field of language object")
    @Column(name = "tech_name", unique = true)
    private String techName;

    @OneToMany(mappedBy = "technology")
    private Set<CurriculumVitaeTechnologyLink> cvTechnology;
}
