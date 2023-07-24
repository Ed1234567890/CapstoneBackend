package main.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.entities.Question;

@Repository
public interface QuestionRepo extends ListCrudRepository<Question, Integer> {
    @Query("SELECT o FROM Question o WHERE o.category IN (:categories) AND o.active = true ORDER BY RAND() LIMIT 10")
    List<Question> find10Questions(@Param("categories")String[] categories);
    @Query("SELECT DISTINCT o.category FROM Question o ORDER BY o.category")
    List<String> getAllCategory();
    List<Question> findByCategoryIn(List<String> category);
}
