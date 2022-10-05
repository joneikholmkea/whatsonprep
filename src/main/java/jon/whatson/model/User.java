package jon.whatson.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter // laver getter, setter, toString(), hashCode()
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany
    @JoinTable(
            name = "venue_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "venue_id")
    )
//    @JsonBackReference // pga. cirkulær reference i @Restcontroller
//    @EqualsAndHashCode.Exclude // pga. hashCode() fra @Data
    private Set<Venue> likedVenues = new HashSet<>();
}
