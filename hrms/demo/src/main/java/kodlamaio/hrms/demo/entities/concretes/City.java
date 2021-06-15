package kodlamaio.hrms.demo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "advertisements", "experiences"})
public class City implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of city object")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "City name is mandatory.")
    @ApiModelProperty(value = "cityName field of city object")
    @Column(name = "city_name", unique = true)
    private String cityName;

    @OneToMany(mappedBy = "city")
    @ApiModelProperty(value = "advertisements of city object")
    private Set<Advertisement> advertisements;

    @OneToMany(mappedBy = "city")
    @ApiModelProperty(value = "experiences of city object")
    private Set<Experience> experiences;
}
