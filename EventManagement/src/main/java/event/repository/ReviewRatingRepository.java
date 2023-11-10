package event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import event.models.EventRegistrationModel;
import event.models.ReviewRatingModel;

public interface ReviewRatingRepository extends JpaRepository<ReviewRatingModel, Long> {
	@Query(value="SELECT AVG(rating) FROM review_rating WHERE event_registration_id in(select event_registration_id from event_registration where event_id=?)", nativeQuery=true)
	String getRating(long eventId);
	
	List<ReviewRatingModel> findByEventRegistrationModelIn(List<EventRegistrationModel> eventRegistrationModelList); 

	

}
