package event.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import event.models.StudentModel;
import event.repository.StudentRepository;

public class StudentDetailsService implements UserDetailsService {
		@Autowired
		private StudentRepository studentRepository;
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			List<StudentModel> studentModelList = studentRepository.findByEmail(username);
			if(studentModelList.size()==0) {
				throw new UsernameNotFoundException("invalid Student Email Address");
			}
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_STUDENT");
			List<SimpleGrantedAuthority> grandAuthorities = new ArrayList<>();
			grandAuthorities.add(simpleGrantedAuthority);
			return new User(studentModelList.get(0).getEmail(),studentModelList.get(0).getPassword(),grandAuthorities);
		}

	}


