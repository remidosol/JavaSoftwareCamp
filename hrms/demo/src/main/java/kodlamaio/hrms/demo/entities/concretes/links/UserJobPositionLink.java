package kodlamaio.hrms.demo.entities.concretes.links;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pivot_user_job_positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJobPositionLink implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of UserJobPositionLink object")
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ApiModelProperty(value = "User field of UserJobPositionLink object")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_position_id", referencedColumnName = "id")
    @ApiModelProperty(value = "Job position field of UserJobPositionLink object")
    private JobPosition jobPosition;

}