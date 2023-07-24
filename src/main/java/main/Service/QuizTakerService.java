/**
 * Service fo Quiz Taker Controller endpoint
 */
package main.Service;

import main.entities.Question;
import main.entities.Quiz;
import main.entities.Result;
import main.exceptions.ResourceNotFoundException;
import main.model.EndQuizPOJO;
import main.model.QuizPOJO;
import main.model.MarkQuizPOJO;
import main.repository.QuestionRepo;
import main.repository.QuizRepo;
import main.repository.ResultRepo;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuizTakerService {

    private final QuestionRepo questionRepo;
    private final QuizRepo quizRepo;
    private final ResultRepo resultRepo;

    public QuizTakerService(QuestionRepo questionRepo, QuizRepo quizRepo, ResultRepo resultRepo) {
        this.questionRepo = questionRepo;
        this.quizRepo = quizRepo;
        this.resultRepo = resultRepo;
    }

    /**
     * Endpoint for viewing result via id
     * @param id id of result
     * @return result
     * @throws ResourceNotFoundException
     */
    public Result viewResult(int id) throws ResourceNotFoundException{
        Result result = this.resultRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Result not found for this id: " + id));
        return result;
    }
    /**
     * Endpoint for viewing all results
     * @return All results
     * @throws ResourceNotFoundException
     */
    public List<Result> viewAllResult() throws ResourceNotFoundException{
        return this.resultRepo.findAll();
    }
    /**
     * Endpoint for Taking a quiz from a list of category
     * @param categories List of string category
     * @return List of Questions
     */
    public List<Question> takeQuiz(String[] categories){
        List<Question> questionList = this.questionRepo.find10Questions(categories);
        return questionList;
    }
    /**
     * Endpoint for marking quiz, adds quiz and result
     * @param takenQuiz POJO with list of question ID and ans options
     * @return Result
     */
    @Transactional
    public Result markQuiz(MarkQuizPOJO takenQuiz){
        int resultMarks = 0;
        for (int i = 0; i < takenQuiz.getQID().length; i++) {
            Question question = this.questionRepo.findById(takenQuiz.getQID()[i]).orElseThrow(() -> new ResourceNotFoundException(
                    "Question not found"));
            if (question.getAns() == takenQuiz.getSelectedAns()[i])
                resultMarks++;
        }
        List<String> qnTemplate = new ArrayList<>();
        for (int q : takenQuiz.getQID()) {
            qnTemplate.add(String.valueOf(q));
        }
        Quiz quiz = new Quiz();
        quiz.setQuizTemplate(qnTemplate);
        this.quizRepo.save(quiz);

        Result result = new Result();
                result.setScore(resultMarks);
                result.setDateTaken(takenQuiz.getDate());
                result.setQuizID(quiz);

        List<String> selAnsStr = new ArrayList<>();
        for (int i : takenQuiz.getSelectedAns()) {
            selAnsStr.add(String.valueOf(i));
        }
                result.setSelectedAns(selAnsStr);

        result = this.resultRepo.save(result);


        EndQuizPOJO end = new EndQuizPOJO(resultMarks,takenQuiz.getQID().length);

        return result;

    }
    /**
     * Endpoint for viewing quiz
     * @param id id of the quiz
     * @return Quiz
     * @throws ResourceNotFoundException
     */
    public QuizPOJO viewQuiz(int id) throws ResourceNotFoundException{
        Result result = this.resultRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Result not found for id: "+id));
        QuizPOJO qPOJO = new QuizPOJO();
        qPOJO.setID(result.getQuizID().getID());
        List<Question> lq = new ArrayList<>();
        for (String str: result.getQuizID().getQuizTemplate()) {
            System.out.println(str);
            lq.add(this.questionRepo.findById(Integer.parseInt(str)).get());
        }
        qPOJO.setQuiz(lq);
        qPOJO.setSelectedAns(result.getSelectedAns());
        return qPOJO;
    }
    /**
     * Endpoint for Retaking a quiz
     * @param id id of the quiz
     * @return List of questions
     * @throws ResourceNotFoundException
     */
    public List<Question> retakeQuiz(int id){
       Quiz quiz = this.quizRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
               "Questions not found for this Quiz: " + id));
       List<Question> questionList = new ArrayList<>();
        for (String q: quiz.getQuizTemplate()) {
            questionList.add(this.questionRepo.findById(Integer.valueOf(q)).get());
        }

        return questionList;
    }
    @Transactional
    public Result RemarkQuiz(int id, MarkQuizPOJO takenQuiz){
        int resultMarks = 0;
        for (int i = 0; i < takenQuiz.getQID().length; i++) {
            Question question = this.questionRepo.findById(takenQuiz.getQID()[i]).orElseThrow(() -> new ResourceNotFoundException(
                    "Question not found"));
            if (question.getAns() == takenQuiz.getSelectedAns()[i])
                resultMarks++;
        }
        List<String> qnTemplate = new ArrayList<>();
        for (int q : takenQuiz.getQID()) {
            qnTemplate.add(String.valueOf(q));
        }
        Quiz quiz = this.quizRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Questions not found for this Quiz: " + id));

        Result result = new Result();
        result.setID(0);
        result.setScore(resultMarks);
        result.setDateTaken(takenQuiz.getDate());
        result.setQuizID(quiz);

        List<String> selAnsStr = new ArrayList<>();
        for (int i : takenQuiz.getSelectedAns()) {
            selAnsStr.add(String.valueOf(i));
        }
        result.setSelectedAns(selAnsStr);
        result = this.resultRepo.save(result);

//        EndQuizPOJO end = new EndQuizPOJO(resultMarks,takenQuiz.getQID().length);

        return result;

    }
}
