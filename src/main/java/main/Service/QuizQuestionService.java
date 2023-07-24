/**
 * Service for QuizQuestion Endpoint
 */
package main.Service;
import main.entities.Question;
import main.exceptions.ResourceNotFoundException;
import main.repository.QuestionRepo;
import java.util.List;

public class QuizQuestionService {

    private final QuestionRepo questionRepo;

    public QuizQuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    /**
     * Endpoint for getting all Questions
     * @return list of Question object
     * @throws ResourceNotFoundException
     */
    public List<Question> getAllQuestion() throws ResourceNotFoundException {
        List<Question> allQuestion = this.questionRepo.findAll();
        return allQuestion;
    }

    /**
     * Endpoint for viewing one question
     * @param id ID of the question
     * @return Selected Question
     * @throws ResourceNotFoundException
     */
    public Question viewQuestion(int id) throws ResourceNotFoundException{
        Question question = this.questionRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(
                "Question not found for this id: " + id));

        return question;
    }
    /**
     * Endpoint for adding a question
     * @param newQuestion Question object
     * @return added question
     */
    public Question addQuestion(Question newQuestion){
        Question question = this.questionRepo.save(newQuestion);
        return question;
    }
    /**
     * Endpoint for updating question, skip fields that are empty
     * @param updatedQuestion Question object
     * @return updated Question
     * @throws ResourceNotFoundException
     */
    public Question updateQuestion(Question updatedQuestion) throws ResourceNotFoundException{

        Question currentQuestion = this.questionRepo.findById(updatedQuestion.getQID())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Question not found for this id: " + updatedQuestion.getQID()));

        if(!updatedQuestion.getQnStr().isEmpty())
            currentQuestion.setQnStr(updatedQuestion.getQnStr());
        if(!updatedQuestion.getCategory().isEmpty())
            currentQuestion.setCategory(updatedQuestion.getCategory());

        currentQuestion.setActive(updatedQuestion.isActive());
        if(!updatedQuestion.getOption1().isEmpty())
            currentQuestion.setOption1(updatedQuestion.getOption1());
        if(!updatedQuestion.getOption2().isEmpty())
            currentQuestion.setOption2(updatedQuestion.getOption2());
        if(!updatedQuestion.getOption3().isEmpty())
            currentQuestion.setOption3(updatedQuestion.getOption3());
        if(!updatedQuestion.getOption4().isEmpty())
            currentQuestion.setOption4(updatedQuestion.getOption4());
        if(updatedQuestion.getAns() >= 1 && updatedQuestion.getAns() <= 4)
            currentQuestion.setAns(updatedQuestion.getAns());

        return this.questionRepo.save(currentQuestion);
    }
    /**
     * Endpoint for getting distinct list of category
     * @return String list of all categories
     * @throws ResourceNotFoundException
     */
    public List<String> getAllCategory() throws ResourceNotFoundException{
        List<String> allCategory = this.questionRepo.getAllCategory();
        return allCategory;
    }
    /**
     * Endpoint for Getting Question searching by category
     * @param cats String list of category
     * @return Questions
     * @throws ResourceNotFoundException
     */
    public List<Question> findByCategory(List<String> cats) throws ResourceNotFoundException{
        List<Question> categories = this.questionRepo.findByCategoryIn(cats);
        return categories;
    }

}
