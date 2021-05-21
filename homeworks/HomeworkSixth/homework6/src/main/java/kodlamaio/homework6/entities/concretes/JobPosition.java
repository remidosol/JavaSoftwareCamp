package kodlamaio.homework6.entities.concretes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

import kodlamaio.homework6.entities.abstracts.IEntity;

@Entity
@Table(name = "job_positions")
@Getter @Setter @NoArgsConstructor
public class JobPosition implements IEntity, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
