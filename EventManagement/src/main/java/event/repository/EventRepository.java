package event.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import event.models.EventModel;

public interface EventRepository extends JpaRepository<EventModel, Long> {


	List<EventModel> findByEventTitle(String eventTitle);


	List<EventModel> findByEventTitleLike(String string);


	List<EventModel> findByEventTitleLikeAndStartDateAfter(String string, Date date);


	List<EventModel> findByEventTitleLikeAndStartDateAfterOrStartDate(String string, Date date, Date date2);
	
	

}
