package main.entities;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import main.entities.converter.AnswerListConverter;

@Entity
@Table(name="Result")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Result {
    @Id
    private int ID;
    private int score;
    private LocalDate dateTaken;
    @ManyToOne
    @JoinColumn(name = "quizID",referencedColumnName = "ID")
    private Quiz quizID;
    @Convert(converter = AnswerListConverter.class)
    private List<String> selectedAns;

}
