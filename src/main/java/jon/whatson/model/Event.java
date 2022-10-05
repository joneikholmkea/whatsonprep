package jon.whatson.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data // ... hashCode()
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String venue;
    private Timestamp timestamp;  // format: "2022-09-22T07:08:52.713+00:00"
    @ManyToOne
    @JsonBackReference // pga. cirkulær reference i @Restcontroller
    @EqualsAndHashCode.Exclude // pga. hashCode() fra @Data
    private Band band;


}
