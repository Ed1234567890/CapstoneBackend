package main.repository;

import main.entities.Quiz;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends ListCrudRepository<Quiz, Integer> {

}
