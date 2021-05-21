package kodlamaio.homework6.entities.concretes;

import kodlamaio.homework6.entities.abstracts.IEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "employers")
@Getter @Setter @NoArgsConstructor
public class Employer implements IEntity, Serializable {
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

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
