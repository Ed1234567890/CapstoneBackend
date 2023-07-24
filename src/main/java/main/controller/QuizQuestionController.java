/**
 * End points for Question viewing, adding, updating questions, and getting category
 */

package main.controller;
import java.util.List;
import main.repository.QuestionRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.entities.Question;
import main.exceptions.ResourceNotFoundException;
import main.Service.QuizQuestionService;

@RestController
public class QuizQuestionController {
    private final QuizQuestionService quizQuestionService;

    public QuizQuestionController(QuestionRepo questionRepo) {
        this.quizQuestionService = new QuizQuestionService(questionRepo);
    }

    /**
     * Endpoint for getting all Questions
     * @return list of Question object
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/AllQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() throws ResourceNotFoundException{
       List<Question> allQuestion = quizQuestionService.getAllQuestion();
       if(allQuestion.isEmpty())
          throw new ResourceNotFoundException("No Questions Found");
       return ResponseEntity.ok().body(allQuestion);
    }

    /**
     * Endpoint for viewing one question
     * @param id ID of the question
     * @return Selected Question
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ViewQuestion/{id}")
    public ResponseEntity viewQuestion(@PathVariable(value = "id") int id) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(quizQuestionService.viewQuestion(id));
    }

    /**
     * Endpoint for adding a question
     * @param newQuestion Question object
     * @return added question
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/AddQuestion")
    public ResponseEntity addQuestion(@RequestBody Question newQuestion){
        Question question = quizQuestionService.addQuestion(newQuestion);
        return ResponseEntity.ok().body(question);
    }

    /**
     * Endpoint for updating question
     * @param updatedQuestion Question object
     * @return updated Question
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/UpdateQuestion")
    public ResponseEntity updateQuestion(@RequestBody Question updatedQuestion) throws ResourceNotFoundException{
        Question currentQuestion = quizQuestionService.updateQuestion(updatedQuestion);
        return ResponseEntity.ok().body(currentQuestion);
    }

    /**
     * Endpoint for getting distinct list of category
     * @return String list of all categories
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/AllCategory")
    public ResponseEntity getAllCategory() throws ResourceNotFoundException{
        List<String> allCategory = quizQuestionService.getAllCategory();
            if (allCategory.isEmpty())
                throw new ResourceNotFoundException("No category found");
        return ResponseEntity.ok().body(allCategory);
    }

    /**
     * Endpoint for Getting Question searching by category
     * @param cats String list of category
     * @return Questions
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/findByCategory")
    public ResponseEntity findByCategory(@RequestBody List<String> cats) throws ResourceNotFoundException{
        List<Question> categories = quizQuestionService.findByCategory(cats);

        if(categories.isEmpty())
            throw new ResourceNotFoundException("Questions not found");
        return ResponseEntity.ok().body(categories);
    }
}
