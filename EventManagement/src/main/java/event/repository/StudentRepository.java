package event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import event.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

	List<StudentModel> findByEmail(String email);

	List<StudentModel> findByPhone(String phone);

	List<StudentModel> findByEmailOrPhone(String email, String phone);

	
}

