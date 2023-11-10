package event.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import event.models.StaffModel;

@Repository
public interface StaffRepository extends JpaRepository<StaffModel, Long> {

	List<StaffModel> findByEmail(String email);

	List<StaffModel> findByPhone(String phone);
	
	StaffModel findByName(String name);

	List<StaffModel> findByEmailOrPhone(String email, String phone);


}
