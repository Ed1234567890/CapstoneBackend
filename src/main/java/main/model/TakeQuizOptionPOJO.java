/**
 * POJO to take in parameters as Quiz options
 */

package main.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TakeQuizOptionPOJO {
    List<String> categories;
}
