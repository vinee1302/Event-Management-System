 package event.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import event.models.EventModel;
import event.models.StaffModel;
import event.repository.EventRepository;
import event.repository.ReviewRatingRepository;
import event.repository.StaffRepository;
import event.utils.ImageUpload;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EventService {
	@Autowired
	private EventRepository  eventRepository;
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private ReviewRatingRepository reviewRatingRepository;
	
	@Autowired
	private ImageUpload imageUpload;
	
	public String hostEvent1(EventModel eventModel, MultipartFile poster2,String name,String authority) {
		try {
			List<EventModel> eventModelList = eventRepository.findByEventTitle(eventModel.getEventTitle());
			if(eventModelList.size()==0) {
				if(poster2== null) {
					eventModel.setPoster(null);
				}else {
					if(imageUpload.uploadImage(poster2)){
						System.out.println("Picture Added Successfully");
					}
					eventModel.setPoster(Base64.getEncoder().encodeToString(poster2.getBytes()));
				}
				System.err.println(eventModel.getCostPerPerson());
				eventModel.setStartDate2(eventModel.getStartDate2().replace("T", " "));
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM");
				
				eventModel.setStartDate(simpleDateFormat.parse(eventModel.getStartDate2()));
				System.out.println(eventModel.getStartDate());
				if(authority.equalsIgnoreCase("ROLE_STAFF")) {
					StaffModel staffModel = staffRepository.findByEmail(name).get(0);
					eventModel.setStaffModel(staffModel);
				}
				eventModel = eventRepository.save(eventModel);
				eventRepository.save(eventModel);
				return "Event added Successfully";
		}else {
			return "Duplicate Event details";
		}
		
	}catch (Exception e) {
		System.out.println(e);
	}
	return "Event Added";
	}

	public List<EventModel> getEvent(String eventTitle, String role) {
		List<EventModel>eventModelList = new ArrayList<EventModel>();
		List<EventModel>eventModelList2 = new ArrayList<EventModel>();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		System.out.println(calendar.getTime());
		if(role.equalsIgnoreCase("ROLE_STUDENT")) {
			eventModelList = eventRepository.findByEventTitleLikeAndStartDateAfter("%"+eventTitle+"%", calendar.getTime());
		} else {
			eventModelList = eventRepository.findByEventTitleLike("%"+eventTitle+"%");
		}
		System.out.println(eventModelList.size());
		Iterator<EventModel>eventModeliterator = eventModelList.iterator();
		 while(eventModeliterator.hasNext()) {
		 EventModel eventModel =(EventModel)eventModeliterator.next();
		 String rating =reviewRatingRepository.getRating(eventModel.getEventId());
		 if(rating!=null) {
			 double roundOffRating =Math.round(Double.parseDouble(rating)*100.0)/100.0;
			 eventModel.setRating(""+roundOffRating);
		 }else {
			 eventModel.setRating(rating);
		 }
		 eventModelList2.add(eventModel);
	 }
		 
 	 return eventModelList2;
		 
	}

}


