package event.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import event.models.EventRegistrationModel;
import event.service.EventRegistrationService;

@Controller
public class EventRegistrationController {
	@Autowired
	private EventRegistrationService eventRegistrationService;
	
	@GetMapping("bookEvent")
	public String bookEvent(Model model,@RequestParam("eventId")long eventId,@RequestParam("numberOfTickets")long numberOfTickets,Principal principal) {
		EventRegistrationModel eventRegistrationModel = eventRegistrationService.bookEvent(eventId,numberOfTickets,principal.getName());
		model.addAttribute("eventRegistrationModel", eventRegistrationModel);
		return "bookEvent";
	}
	
	@GetMapping("bookEvent1")
	public String bookEvent1(Model model,@RequestParam("eventRegistrationId")long eventRegistrationId,@RequestParam("donations")long donations) {
		 String message = eventRegistrationService.bookEvent1(eventRegistrationId,donations);
		model.addAttribute("message", message);
		return "message";

	}
	
	@GetMapping("viewbookEvent")
	public String viewbookEvent(Model model,Principal principal) {
		List<EventRegistrationModel>eventRegistrationList = eventRegistrationService.viewbookEvent(principal.getName());
		model.addAttribute("eventRegistrationList", eventRegistrationList);
		return "viewbookEvent";
	}
	@GetMapping("cancelTickets")
	public String cancelTickets(@RequestParam("eventRegistrationId")long eventRegistrationId,Model model) {
		String message = eventRegistrationService.cancelTickets(eventRegistrationId);
		model.addAttribute("message", message);
		return "message";
	}
	
}
