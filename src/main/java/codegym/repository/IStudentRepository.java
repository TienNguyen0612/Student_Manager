package codegym.repository;

import codegym.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student, Integer> {
    Iterable<Student> findAllByNameContaining(String name);
}
