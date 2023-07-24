package main.entities;
import jakarta.persistence.*;
import lombok.*;
import main.entities.converter.QuestionListConverter;
import java.util.List;

@Entity
@Table(name="Quiz")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ID;
//    private int passingMark;
    @Convert(converter = QuestionListConverter.class)
    private List<String> quizTemplate;

}
