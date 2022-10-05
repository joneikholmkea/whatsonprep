package jon.whatson.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "likedVenues")
    @JsonBackReference // pga. cirkulær reference i @Restcontroller
//    @EqualsAndHashCode.Exclude // pga. hashCode() fra @Data
    private Set<User> userLikes = new HashSet<>();
}
