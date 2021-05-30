package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "employers")
@Data
@NoArgsConstructor @AllArgsConstructor
public class Employer implements IEntity, Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    @ApiModelProperty(value = "Unique id field of employer object")
    private Long id;

    @NotNull
    @Column(name = "company_name")
    @Size(min = 2, message = "Your company name must be grater than 2 chars.")
    @ApiModelProperty(value = "companyName field of employer object")
    private String companyName;

    @NotNull
    @Column(name = "mobile_number")
    @ApiModelProperty(value = "Unique mobileNumber field of employer object")
    private String mobileNumber;

    @NotNull
    @Column(name = "is_email_confirmed", nullable = false, columnDefinition = "boolean default false")
    @ApiModelProperty(value = "isEmailConfirmed field of employer object")
    private boolean isEmailConfirmed;

    @NotNull
    @Column(name = "is_confirmed_by_staff", nullable = false, columnDefinition = "boolean default false")
    @ApiModelProperty(value = "isConfirmedByStaff field of employer object")
    private boolean isConfirmedByStaff;

    @NotNull
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    @ApiModelProperty(value = "isActive field of employer object")
    private boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(value = "createdAt field of employer object")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(value = "updatedAt field of employer object")
    private Date updatedAt;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @ApiModelProperty(value = "user field of employer object")
    private User user;

    @OneToMany(mappedBy="employer")
    @ApiModelProperty(value = "advertisements of employer object")
    private List<Advertisement> advertisements;
}
