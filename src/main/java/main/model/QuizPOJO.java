/**
 * POJO for view quiz
 */

package main.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.entities.Question;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuizPOJO {

    private int ID;
    private List<Question> quiz;
    private List<String> selectedAns;
}
