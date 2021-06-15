package kodlamaio.hrms.demo.entities.concretes.links;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import kodlamaio.hrms.demo.entities.concretes.Advertisement;
import kodlamaio.hrms.demo.entities.concretes.JobSeeker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pivot_job_seeker_advertisements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerAdvertisementLink implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of JobSeekerAdvertisementLink object")
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "advertisement_id", referencedColumnName = "id")
    @ApiModelProperty(value = "Advertisement field of JobSeekerAdvertisementLink object")
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", referencedColumnName = "id")
    @ApiModelProperty(value = "Job seeker field of JobSeekerAdvertisementLink object")
    private JobSeeker jobSeeker;

}
