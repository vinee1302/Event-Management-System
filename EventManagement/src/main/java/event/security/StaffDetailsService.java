package event.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import event.models.StaffModel;
import event.repository.StaffRepository;

public class StaffDetailsService implements UserDetailsService {
	@Autowired
	private StaffRepository staffRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<StaffModel> staffModelList = staffRepository.findByEmail(username);
		if(staffModelList.size()==0) {
			throw new UsernameNotFoundException("invalid Email Address");
		}
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_STAFF");
		List<SimpleGrantedAuthority> grandAuthorities = new ArrayList<>();
		grandAuthorities.add(simpleGrantedAuthority);
		return new User(staffModelList.get(0).getEmail(),staffModelList.get(0).getPassword(),grandAuthorities);
	}
}
