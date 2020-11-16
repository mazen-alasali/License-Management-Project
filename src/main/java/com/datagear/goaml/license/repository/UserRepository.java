package com.datagear.goaml.license.repository;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import javax.persistence.Query;

import com.datagear.goaml.license.model.License;
import com.datagear.goaml.license.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
  	
//	@Query(value="SELECT * FROM licenseDB.user ORDER BY creation_date ASC LIMIT 3;",
//	nativeQuery= true)
//	public List<User> findTopTenUserCreated();
	public List<User> findTop3ByOrderByCreationDateAsc();
	
//	@Query(value="SELECT * FROM licenseDB.user ORDER BY creation_date desc LIMIT 3;",
//			nativeQuery= true)
//	public List<User> findTopTenEarlyUserRegistered(); 
//	findTopTenEarlyUserRegistered(); 
	public List<User> findTop3ByOrderByCreationDateDesc();
	
//	findFirstByOrderByLastnameAsc();

	
	
	
	public User findByUserName(String userName);
	
//	public User findByPassword(String password);
//	
//	public User loginUser(String userName);
	
//	public String forgetPassword(String userName);
//	
//	public User resetPassword(User user, String Password);

}
