package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements IEntity, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "roleName is mandatory.")
    @NotEmpty(message = "roleName is mandatory.")
    @NotBlank(message = "roleName is mandatory.")
    @ApiModelProperty(value = "roleName field of role object")
    @Column(name = "role_name", unique = true, nullable = false)
    private String roleName;

    @CreationTimestamp
    @ApiModelProperty(value = "createdAt field of role object")
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @ApiModelProperty(value = "updatedAt field of role object")
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "pivot_roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}