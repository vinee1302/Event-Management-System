package event.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import event.models.StudentModel;
import event.repository.StudentRepository;
import event.utils.ImageUpload;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentService {
		@Autowired
		private StudentRepository  studentRepository;
		@Autowired
		private ImageUpload imageUpload;
		
		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;
		
		public String addStudent1(StudentModel studentModel, MultipartFile profilePicture2) {
			try {
				List<StudentModel> studentModelList = studentRepository.findByEmailOrPhone(studentModel.getEmail(), studentModel.getPhone());
				if(studentModelList.size()==0) {
					if(profilePicture2== null) {
						studentModel.setProfilePicture(null);
					}else {
						if(imageUpload.uploadImage(profilePicture2)){
							System.out.println("Picture Added Successfully");
					}
						studentModel.setProfilePicture(Base64.getEncoder().encodeToString(profilePicture2.getBytes()));
				}
				studentModel.setPassword(bCryptPasswordEncoder.encode(studentModel.getPassword()));
				studentRepository.save(studentModel);
				return "student added Successfully";
			}else {
				return "Duplicate Student Details";
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "student Added";
		}

		public List<StudentModel> getStudent2() {
				List<StudentModel>StudentModelList = new ArrayList<StudentModel>();
				StudentModelList = studentRepository.findAll();
				//00 01 10 11
//				if(name.equalsIgnoreCase("")&& email.equalsIgnoreCase("")) {
//					StudentModelList = studentRepository.findAll();
//				}else if(name.equalsIgnoreCase("")&& !email.equalsIgnoreCase("")) {
//					
//					StudentModelList = studentRepository.findByEmail(email);
//				}else if(!name.equalsIgnoreCase("")&& email.equalsIgnoreCase("")) {
//					StudentModelList = studentRepository.findByName(name);
//				}else if(!name.equalsIgnoreCase("")&& !email.equalsIgnoreCase("")) {
//					StudentModelList = studentRepository.findByNameAndEmail(name,email);
//				}
				return StudentModelList;	
				}
	}	
		
					
