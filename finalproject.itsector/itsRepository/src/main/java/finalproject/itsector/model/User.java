package finalproject.itsector.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
//Model Class
@Entity
@Setter
@Getter
@Table(name = "users")
public class User extends BaseModel{

    @Column(nullable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(name = "createdTimestamp", nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();
    @Column(name = "updatedTimestamp")
    private LocalDate updatedAt;
}