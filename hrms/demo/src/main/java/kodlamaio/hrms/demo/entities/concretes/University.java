package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.links.CurriculumVitaeUniversityDepartmentLink;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "universities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "educations"})
public class University implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of university object")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name is mandatory.")
    @ApiModelProperty(value = "name field of university object")
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "university")
    private Set<CurriculumVitaeUniversityDepartmentLink> educations;
}
