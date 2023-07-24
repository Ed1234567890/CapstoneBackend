/**
 * End points for related to taking quiz
 */
package main.controller;

import main.Service.QuizTakerService;
import main.entities.Question;
import main.exceptions.ResourceNotFoundException;
import main.model.QuizPOJO;
import main.model.MarkQuizPOJO;
import main.repository.QuestionRepo;
import main.repository.QuizRepo;
import main.repository.ResultRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizTakerController {

    private final QuizTakerService quizTakerService;

    public QuizTakerController(QuestionRepo questionRepo, QuizRepo quizRepo, ResultRepo resultRepo) {
        this.quizTakerService = new QuizTakerService(questionRepo,quizRepo,resultRepo);
    }

    /**
     * Endpoint for viewing result via id
     * @param id id of result
     * @return result
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ViewResult/{id}")
    public ResponseEntity viewResult(@PathVariable(value = "id") int id) throws ResourceNotFoundException{

        return ResponseEntity.ok().body(this.quizTakerService.viewResult(id));
    }

    /**
     * Endpoint for viewing all results
     * @return All results
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ViewAllResults")
    public ResponseEntity viewAllResult() throws ResourceNotFoundException{
        return ResponseEntity.ok().body(this.quizTakerService.viewAllResult());
    }

    /**
     * Endpoint for Taking a quiz from a list of category
     * @param category List of string category
     * @return List of Questions
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/TakeQuiz")
    public ResponseEntity takeQuiz(@RequestParam String[] category){
        List<Question> questionList = this.quizTakerService.takeQuiz(category);
        if(questionList.isEmpty())
            throw new ResourceNotFoundException("No Questions found. Kindly choose other options e.g. different category.");
        return ResponseEntity.ok().body(questionList);
    }

    /**
     * Endpoint for marking quiz
     * @param takenQuiz POJO with list of question ID and ans options
     * @return Result
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/MarkQuiz")
    public ResponseEntity markQuiz(@RequestBody MarkQuizPOJO takenQuiz){
        return ResponseEntity.ok().body(this.quizTakerService.markQuiz(takenQuiz));
    }

    /**
     * Endpoint for viewing quiz
     * @param id id of the quiz
     * @return Quiz
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/ViewQuiz/{id}")
    public ResponseEntity viewQuiz(@PathVariable(value = "id") int id)
            throws ResourceNotFoundException{
        QuizPOJO quiz = this.quizTakerService.viewQuiz(id);
        return ResponseEntity.ok().body(quiz);
    }

    /**
     * Endpoint for Retaking a quiz
     * @param id id of the quiz
     * @return List of questions
     * @throws ResourceNotFoundException
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/RetakeQuiz/{id}")
    public ResponseEntity takeSameQuizAgain(@PathVariable(value = "id") int id) throws ResourceNotFoundException{
        List<Question> questionList = quizTakerService.retakeQuiz(id);
        if(questionList.isEmpty())
            throw new ResourceNotFoundException("No Questions found. Kindly choose other options.");
        return ResponseEntity.ok().body(questionList);
    }
    /**
     * Endpoint for re-marking quiz
     * @param takenQuiz POJO with list of question ID and ans options
     * @return Result
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/RemarkQuiz/{id}")
    public ResponseEntity RemarkQuiz(@PathVariable(value = "id") int id,@RequestBody MarkQuizPOJO takenQuiz){
        return ResponseEntity.ok().body(this.quizTakerService.RemarkQuiz(id,takenQuiz));
    }
}
