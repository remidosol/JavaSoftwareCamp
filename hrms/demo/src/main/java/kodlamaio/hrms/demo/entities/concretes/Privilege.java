package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "privileges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Privilege implements IEntity, Serializable {

    @Id
    @GeneratedValue
    private Long id;


    @NotNull(message = "privilegeName is mandatory.")
    @NotEmpty(message = "privilegeName is mandatory.")
    @NotBlank(message = "privilegeName is mandatory.")
    @ApiModelProperty(value = "privilegeName field of privilege object")
    @Column(name = "privilege_name", unique = true, nullable = false)
    private String privilegeName;

    @CreationTimestamp
    @ApiModelProperty(value = "createdAt field of privilege object")
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @ApiModelProperty(value = "updatedAt field of privilege object")
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
