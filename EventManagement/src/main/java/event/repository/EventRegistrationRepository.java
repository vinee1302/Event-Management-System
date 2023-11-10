package event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import event.models.EventModel;
import event.models.EventRegistrationModel;
import event.models.StudentModel;

public interface EventRegistrationRepository extends JpaRepository<EventRegistrationModel, Long> {

	List<EventRegistrationModel> findBystudentModel(StudentModel studentModel);

	List<EventRegistrationModel> findByEventModel(EventModel eventModel);
	

}
