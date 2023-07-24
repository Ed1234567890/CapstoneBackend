package main.repository;

import main.entities.Result;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends ListCrudRepository<Result, Integer>{

}
