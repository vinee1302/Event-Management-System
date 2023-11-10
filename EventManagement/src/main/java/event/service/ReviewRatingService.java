package event.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import event.models.EventModel;
import event.models.EventRegistrationModel;
import event.models.ReviewRatingModel;
import event.repository.EventRegistrationRepository;
import event.repository.EventRepository;
import event.repository.ReviewRatingRepository;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class ReviewRatingService {
	@Autowired
	EventRegistrationRepository eventRegistrationRepository;
	
	@Autowired
	ReviewRatingRepository reviewRatingRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	
	
	public String review1(ReviewRatingModel reviewRatingModel) {
		EventRegistrationModel eventRegistrationModel = eventRegistrationRepository.findById(reviewRatingModel.getEventRegistrationId()).get();
		reviewRatingModel.setEventRegistrationModel(eventRegistrationModel);
		reviewRatingModel.setDate(new Date());
		reviewRatingRepository.save(reviewRatingModel);
		return "Review sent successfully";
	}

	public List<ReviewRatingModel> viewreview(long eventId) {
		EventModel eventModel = eventRepository.findById(eventId).get();
 		List<EventRegistrationModel> eventRegistrationModelList = eventRegistrationRepository.findByEventModel(eventModel);
		List<ReviewRatingModel>reviewRatingList = reviewRatingRepository.findByEventRegistrationModelIn(eventRegistrationModelList);
		return reviewRatingList;
	}
}


