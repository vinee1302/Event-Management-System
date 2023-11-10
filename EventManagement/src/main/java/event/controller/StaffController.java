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
import org.springframework.web.multipart.MultipartFile;

import event.models.StaffModel;
import event.service.StaffService;

@Controller
public class StaffController {
	@Autowired
	private StaffService staffService;
	
	@GetMapping("index")
	public String index() {
		return "index";
	}
	
	@GetMapping("staffLogin")
	public String staffLogin() {
		return "staffLogin";
	}
	
	
	
	@GetMapping("addStaff")
	public String addStaff(Model model) {
		StaffModel staffModel = new StaffModel();
		model.addAttribute("staffModel", staffModel);
		return "addStaff";
	}
	@PostMapping("addStaff1")
	public String addStaff1(@ModelAttribute("staffModel") StaffModel staffModel,Model model,@RequestParam("profilePicture2") MultipartFile profilePicture2) {
		String amessage=staffService.addStaff1(staffModel, profilePicture2);
		model.addAttribute("amessage",amessage );
		return "amessage";
	

	}
	@GetMapping("viewStaff")
	public String viewStaff(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
			List<StaffModel> staffModelList= staffService.getStaff2();
			model.addAttribute("staffModelList",staffModelList);
		}
		return "viewStaff";
	}
	@GetMapping("staffHome")
	public String staffHome() {
		return "staffHome";
	}
	
}
