package main.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int qID;
    private String qnStr, category;
    private boolean active;
    private String option1, option2, option3, option4;
    private byte ans;

}
