package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "job_positions")
@Data
@NoArgsConstructor @AllArgsConstructor
public class JobPosition implements IEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "Unique id field of job position object")
    private Long id;

    @NotNull
    @Column(name = "position_name", unique = true)
    @Size(min = 2, message = "Your position name must be grater than 2 chars.")
    @ApiModelProperty(value = "positionName field of job position object")
    private String positionName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "pivot_user_job_positions",
            joinColumns =
                    { @JoinColumn(name = "job_position_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "id") })
    @ApiModelProperty(value = "users of job position object")
    private Set<User> users;

    @OneToMany(mappedBy="jobPosition")
    @ApiModelProperty(value = "advertisements of job position object")
    private Set<Advertisement> advertisements;

}
