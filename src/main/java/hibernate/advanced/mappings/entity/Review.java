package hibernate.advanced.mappings.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="review")
public class Review {

    // define fields
    // annotate fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    // define constructor
    public Review() {

    }

    public Review(String comment) {
        this.comment = comment;
    }

}
