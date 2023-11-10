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

import event.models.StudentModel;
import event.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
		
	@GetMapping("adminLogin")
	public String adminLogin() {
		return "adminLogin";
	}
	@GetMapping("adminHome")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("studentLogin")
	public String studentLogin() {
		return "studentLogin";
	}
	
	
	@GetMapping("addStudent")
	public String addStudent(Model model) {
		StudentModel studentModel = new StudentModel();
		model.addAttribute("studentModel", studentModel);
		return "addStudent";
	}
	@PostMapping("addStudent1")
	public String addStudent1(@ModelAttribute("studentModel") StudentModel studentModel, Model model,@RequestParam("profilePicture2") MultipartFile profilePicture2) {
		String message=studentService.addStudent1(studentModel,profilePicture2);
		model.addAttribute("message",message );
		return "message";
	

	}
	@GetMapping("viewStudent")
	public String viewStudent(Model model,@AuthenticationPrincipal UserDetails userDetails) {
		Iterator<? extends GrantedAuthority> grantedAuthories = userDetails.getAuthorities().iterator();
		while (grantedAuthories.hasNext()) {
			GrantedAuthority grantedAuthority = (GrantedAuthority) grantedAuthories.next();
			model.addAttribute("role", grantedAuthority.getAuthority());
			List<StudentModel> studentModelList= studentService.getStudent2();
			model.addAttribute("studentModelList",studentModelList);
		}
		return "viewStudent";
	}
	@GetMapping("studentHome")
	public String studentHome() {
		return "studentHome";
	}

}
