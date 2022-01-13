package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	//TESTS PLACING NEW USER OBJECT INTO DATABASE WITH ONE ROLE
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userNamHM = new User("nam@codejava.net", "nam2020", "Nam", "Ha Minh");
		userNamHM.addRole(roleAdmin);
		
		User savedUser = repo.save(userNamHM);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	//TESTS PLACING NEW USER OBJECT INTO DATABASE WITH TWO ROLES
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userRavi = new User("ravi@gmail.com", "ravi2020", "Ravi", "Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		
		User savedUser = repo.save(userRavi);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	//TEST METHOD FOR RETRIEVING ALL USERS FROM DATABASE
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	//TEST METHOD FOR RETRIEVING USER BY ID
	@Test
	public void testGetUserById() {
		User userNam = repo.findById(1).get();
		System.out.println(userNam);
		assertThat(userNam).isNotNull();
	}
	
	//TEST METHOD FOR UPDATING USER DETAILS
	@Test
	public void testUpdateUserDetails() {
		User userNam = repo.findById(1).get();
		userNam.setEnabled(true);
		userNam.setEmail("namjavaprogrammer@gmail.com");
		
		repo.save(userNam);
	}
	
	//TEST METHOD FOR UPDATING USER ROLES
	@Test
	public void testUpdateUserRoles() {
		User userRavi = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSalesperson);
		
		repo.save(userRavi);
	}
	
	//TEST METHOD FOR DELETING USER
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
		}

	//TEST METHOD FOR CHECKING IF EMAIL IS CURRENTLY BEING USED
	@Test
	public void testGetUserByEmail() {
		String email = "ravi@gmail.com";
		User user = repo.getUserByEmail(email);
		
		assertThat(user).isNotNull();
	}
	//TEST METHOD FOR CHECKING IF ID OF USER EXISTS
	@Test
	public void testCountById() {
		Integer id = 1;
		Long countById=repo.countById(id);
		
		assertThat(countById).isNotNull().isGreaterThan(0);
	}
	
	//TEST METHOD FOR ALTERING USER ENABLED STATUS TO FALSE(DISABLED)
	@Test
	public void testDisableUser() {
		Integer id = 6;
		repo.updateEnabledStatus(id, false);
	}
	
	//TEST METHOD FOR ALTERING USER ENABLED STATUS TO TRUE(ENABLED)
	@Test
	public void testEnableUser() {
		Integer id = 6;
		repo.updateEnabledStatus(id, true);
	}
	
	//TEST METHOD FOR PAGINATION, LISTING FIRST PAGE SPECIFICALLY
	@Test
	public void testListFirstPage() {
		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isEqualTo(pageSize);
	}
	
	//TEST METHOD FOR SEARCHING FOR USERS
	public void testSearchUsers() {
		String keyword = "bruce";

		int pageNumber = 0;
		int pageSize = 4;
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = repo.findAll(keyword, pageable);
		
		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isGreaterThan(0);
	}
}
