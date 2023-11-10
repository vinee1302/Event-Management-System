package event.controller;


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

import event.models.ReviewRatingModel;
import event.service.ReviewRatingService;

@Controller
public class ReviewRatingController {
	@Autowired
	private ReviewRatingService reviewRatingService;
	
	@GetMapping("review")
	public String review(Model model,@RequestParam("eventRegistrationId")long eventRegistrationId) {
		ReviewRatingModel reviewRatingModel = new ReviewRatingModel();
		reviewRatingModel.setEventRegistrationId(eventRegistrationId);
		model.addAttribute("eventRegistrationId", eventRegistrationId);
		model.addAttribute("reviewRatingModel", reviewRatingModel);
		return "review";
		
	}
	@PostMapping("review1")
	public String review1(@ModelAttribute("reviewRatingModel") ReviewRatingModel reviewRatingModel,Model model) {
		String message = reviewRatingService.review1(reviewRatingModel);
		model.addAttribute("message",message);
		return "message";
	}

	@GetMapping("viewreview")
	public String viewreview(@RequestParam("eventId")long eventId,@AuthenticationPrincipal UserDetails userDetails,Model model){
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
		List<ReviewRatingModel>reviewRatingList =reviewRatingService.viewreview(eventId);
		model.addAttribute("reviewRatingList", reviewRatingList);

}
		return "viewreview";
	}
}

	


		


