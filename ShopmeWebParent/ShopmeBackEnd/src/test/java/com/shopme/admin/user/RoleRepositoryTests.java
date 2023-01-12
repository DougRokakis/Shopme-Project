package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	
	@Autowired
	private RoleRepository repo;
	
	//TESTING CREATION OF ROLE WITH ID, NAME, AND DESCRIPTION
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Admin", "manage everything");
		Role savedRole = repo.save(roleAdmin);
		
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	//TESTING CREATION OF FOUR NEW ROLES WITH ID, NAME AND DESCRIPTION
	@Test
	public void testCreateRestRoles() {
		Role roleSalesperson = new Role("Salesperson", "manage product price, "
				+"customers, shipping, orders and sales report");
		Role roleEditor = new Role("Editor", "manage categories, "
				+"products, articles, and menus");
		Role roleShipper = new Role("Shipper", "view products, view orders "
				+ "and update order status");
		Role roleAssistant = new Role("Assistant", "manage questions and reviews");
		
		repo.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssistant));
	}

}
