package event.controller;

import java.util.Iterator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@GetMapping("/welcome")
	public String welcome(@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			System.out.println(grantedAuthority.getAuthority());
			if(grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
				return "redirect:/adminHome";
			}else if(grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_STAFF")) {
				return "redirect:/staffHome";
			}else if(grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_STUDENT")) {
				return "redirect:/studentHome";
			}
		}
		return "index";
	}

}
