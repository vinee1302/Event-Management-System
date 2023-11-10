
package event.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import event.models.EventModel;
import event.models.EventRegistrationModel;
import event.models.StudentModel;
import event.repository.EventRegistrationRepository;
import event.repository.EventRepository;
import event.repository.StudentRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EventRegistrationService {
	@Autowired
	EventRegistrationRepository eventRegistrationRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	public EventRegistrationModel bookEvent(long eventId, long numberOfTickets, String email) {
		StudentModel studentModel= studentRepository.findByEmail(email).get(0);
		EventModel eventModel = eventRepository.findById(eventId).get();
		double costPerPerson = Double.parseDouble(eventModel.getCostPerPerson());
		double totalAmount = costPerPerson*numberOfTickets;
		double tax = totalAmount*0.05;
		totalAmount=totalAmount+tax;
		EventRegistrationModel eventRegistrationModel = new EventRegistrationModel();   
		eventRegistrationModel.setNumberOfTickets(numberOfTickets);
		eventRegistrationModel.setStatus("payment processing");
		eventRegistrationModel.setTax(tax);
		eventRegistrationModel.setTotalAmount(totalAmount);
		eventRegistrationModel.setEventModel(eventModel);
		eventRegistrationModel.setStudentModel(studentModel);
		eventRegistrationModel.setDate(new Date());
		eventRegistrationModel=eventRegistrationRepository.save(eventRegistrationModel);
		
		return eventRegistrationModel;
	}

	public String bookEvent1(long eventRegistrationId, long donations) {
		EventRegistrationModel eventRegistrationModel = eventRegistrationRepository.findById(eventRegistrationId).get();
		eventRegistrationModel.setTotalAmount(eventRegistrationModel.getTotalAmount()+donations);
		eventRegistrationModel.setDonations(donations);
		eventRegistrationModel.setStatus("Booked");
		eventRegistrationRepository.saveAndFlush(eventRegistrationModel);
		return "Tickets Booked successfully";
	}

	public List<EventRegistrationModel> viewbookEvent(String name) {
		StudentModel studentModel = studentRepository.findByEmail(name).get(0);
		List<EventRegistrationModel> eventRegistrationList = eventRegistrationRepository.findBystudentModel(studentModel);
		return eventRegistrationList;
	}

	public String cancelTickets(long eventRegistrationId) {
		EventRegistrationModel eventRegistrationModel = eventRegistrationRepository.findById(eventRegistrationId).get();
		eventRegistrationModel.setStatus("Tickets cancelled");
		eventRegistrationRepository.saveAndFlush(eventRegistrationModel);
		return "Tickets Cancelled successfully";
	}

	 
}
