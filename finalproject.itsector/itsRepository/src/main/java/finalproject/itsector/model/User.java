package finalproject.itsector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

//Model Class
@Entity
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "users")
public class User extends BaseModel{

    @Column(nullable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, updatable = false)
    private LocalDateTime creation = LocalDateTime.now();
    private LocalDateTime lastUpdated;
}