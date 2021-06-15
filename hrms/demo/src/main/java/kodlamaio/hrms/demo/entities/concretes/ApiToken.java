package kodlamaio.hrms.demo.entities.concretes;

import io.swagger.annotations.ApiModelProperty;
import kodlamaio.hrms.demo.core.entities.User;
import kodlamaio.hrms.demo.core.enums.TokenNameEnums;
import kodlamaio.hrms.demo.entities.abstracts.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
@Table(name = "api_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiToken implements IEntity, Serializable {
    public static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Unique id field of apiToken object")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @ApiModelProperty(value = "Enumerated token name field of apiToken object")
    @Column(name = "token_name")
    private TokenNameEnums.TokenName tokenName;

    @NotNull(message = "Token is mandatory.")
    @ApiModelProperty(value = "token field of apiToken object")
    @Column(name = "token")
    private String token;

    @ApiModelProperty(value = "expiryDate field of apiToken object")
    @Column(name = "expiry_date")
    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @ApiModelProperty(value = "User field of apiToken object")
    private User user;

    public static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}
