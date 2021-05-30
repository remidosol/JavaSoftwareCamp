package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kodlamaio.hrms.demo.entities.abstracts.IEntity;

@Entity
@Table(name = "job_positions")
@Data
@NoArgsConstructor @AllArgsConstructor
public class JobPosition implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @ApiModelProperty(value = "Unique id field of job position object")
    private Long id;

    @NotNull
    @Column(name = "position_name")
    @Size(min = 2, message = "Your position name must be grater than 2 chars.")
    @ApiModelProperty(value = "positionName field of job position object")
    private String positionName;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(value = "createdAt field of job position object")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(value = "updatedAt field of job position object")
    private Date updatedAt;

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
    private Set<User> users = new HashSet<User>(0);

    @OneToMany(mappedBy="jobPosition")
    @ApiModelProperty(value = "advertisements of job position object")
    private List<Advertisement> advertisements;

}
