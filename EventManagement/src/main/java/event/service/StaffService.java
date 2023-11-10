package event.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import event.models.StaffModel;
import event.repository.StaffRepository;
import event.utils.ImageUpload;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StaffService {
	@Autowired
	private StaffRepository  staffRepository;
	public String addStaff(StaffModel staffModel) {
		List<StaffModel>staffModelList = staffRepository.findByEmail(staffModel.getEmail());
		if(staffModelList.size()>0) {
			return "Duplicate Email Address";
		}
		List<StaffModel>staffModelList2 = staffRepository.findByPhone(staffModel.getPhone());
		if(staffModelList2.size()>0) {
			return "Duplicate Phone number";
		}
		staffRepository.save(staffModel);
		return "Staff Register Successfully";
	}
	
	@Autowired
	private ImageUpload imageUpload;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public String addStaff1(StaffModel staffModel, MultipartFile picture) {
		try {
			List<StaffModel> staffModelList = staffRepository.findByEmailOrPhone(staffModel.getEmail(), staffModel.getPhone());
			if(staffModelList.size()==0) {
				if(picture== null) {
					staffModel.setProfilePicture(null);
				}else {
					if(imageUpload.uploadImage(picture)){
						System.out.println("Picture Added Successfully");
					}
						staffModel.setProfilePicture(Base64.getEncoder().encodeToString(picture.getBytes()));
				}
				staffModel.setPassword(bCryptPasswordEncoder.encode(staffModel.getPassword()));
				staffRepository.save(staffModel);
				return "staff added Successfully";
			}else {
				return "Duplicate Staff Details";
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return "staff Added";
	}

	public List<StaffModel> getStaff2() {
			List<StaffModel>StaffModelList = new ArrayList<StaffModel>();
			StaffModelList = staffRepository.findAll();
			//00 01 10 11
//			if(name.equalsIgnoreCase("")&& email.equalsIgnoreCase("")) {
//				StudentModelList = studentRepository.findAll();
//			}else if(name.equalsIgnoreCase("")&& !email.equalsIgnoreCase("")) {
//				
//				StudentModelList = studentRepository.findByEmail(email);
//			}else if(!name.equalsIgnoreCase("")&& email.equalsIgnoreCase("")) {
//				StudentModelList = studentRepository.findByName(name);
//			}else if(!name.equalsIgnoreCase("")&& !email.equalsIgnoreCase("")) {
//				StudentModelList = studentRepository.findByNameAndEmail(name,email);
//			}
			return StaffModelList;	
			}
}	
	


