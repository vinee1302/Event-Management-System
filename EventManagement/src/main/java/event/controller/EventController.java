
package event.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import event.models.EventModel;
import event.service.EventService;

@Controller
public class EventController {
	@Autowired
	private EventService eventService;
	
	@GetMapping("hostEvent")
	public String hostEvent(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			EventModel eventModel = new EventModel();
			model.addAttribute("eventModel", eventModel);
		model.addAttribute("role",grantedAuthority.getAuthority());
		}
		return "hostEvent";
	
	}
	@PostMapping("hostEvent1")
	public String hostEvent1(@ModelAttribute("eventModel") EventModel eventModel,Model model,@RequestParam("poster2") MultipartFile poster2,Principal principal,@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
			String amessage=eventService.hostEvent1(eventModel,poster2,principal.getName(),grantedAuthority.getAuthority());
			model.addAttribute("amessage",amessage);
			if(grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_STAFF")) {	
			}else {
				return "message";
				
			}
		}
			return "amessage";
	}
	@GetMapping("viewEvent")
	public String viewEvent(Model model,@RequestParam("eventTitle") String eventTitle,@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
			List<EventModel> eventModelList= eventService.getEvent(eventTitle,grantedAuthority.getAuthority());
			model.addAttribute("eventModelList", eventModelList);
		}
		
		return "viewEvent";
	}

}
