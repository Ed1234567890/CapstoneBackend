/**
 * POJO to get a list of answer options selected for marking
 */

package main.model;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MarkQuizPOJO {

    private int[] qID;
    private int[] selectedAns;
    @Temporal(TemporalType.TIME)
    private LocalDate date = LocalDate.now();

}
