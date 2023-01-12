package com.shopme.admin.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.shopme.common.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
	
	//METHOD WITH CUSTOM QUERY TO RETRIEVE USER OBJECT BY EMAIL
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	
	//METHOD TO FIND USER BY ID
	public Long countById(Integer id);
	
	//METHOD WITH CUSTOM QUERY FOR SEARCHING FOR USER BY EITHER ID, EMAIL, FIRST NAME, OR LAST NAME
	@Query("SELECT u FROM User u where CONCAT(u.id, ' ', u.email, ' ', u.firstName, ' ' ,"
			+" u.lastName) LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
	
	//METHOD WITH CUSTOM QUERY TO UPDATE ENABLED STATUS OF USER
	@Query("UPDATE User u SET u.enabled = ?2 WHERE u.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
	
}
