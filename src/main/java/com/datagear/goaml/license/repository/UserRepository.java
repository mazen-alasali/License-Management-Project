package com.datagear.goaml.license.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datagear.goaml.license.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
  	
	public List<User> findTop3ByOrderByCreationDateAsc();
	public List<User> findTop3ByOrderByCreationDateDesc();
	
	public User findByUserName(String userName);
	public User findByUserNameAndPassword(String userName, String password);

}
