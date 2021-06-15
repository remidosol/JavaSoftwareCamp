package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.enums.RoleEnums;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;


@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @ApiModelProperty(value = "Unique id field of staff object")
    private Long id;

    @NotNull
    @Column(name = "first_name")
    @Size(min = 2)
    @ApiModelProperty(value = "firstName field of staff object")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @Size(min = 2)
    @ApiModelProperty(value = "lastName field of staff object")
    private String lastName;

//    @NotNull
//    @Column(name = "role")
//    @Enumerated(EnumType.ORDINAL)
//    @ApiModelProperty(value = "Enumerated role field of staff object")
//    private RoleEnums.StaffRoles role;

    @CreationTimestamp
    @Column(name = "created_at")
    @ApiModelProperty(value = "createdAt field of staff object")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @ApiModelProperty(value = "updatedAt field of staff object")
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ApiModelProperty(value = "user field of staff object")
    private User user;
}
