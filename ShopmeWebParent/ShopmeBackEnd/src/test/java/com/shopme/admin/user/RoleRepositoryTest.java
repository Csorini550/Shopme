package com.shopme.admin.user;

import java.util.List;

import org.assertj.core.api.Assertions;
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
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository repo;
	@Test
	public void testCreateFirstRole() {
		Role roleAdmin= new Role("Admin","manage everything");
		Role savedRole= repo.save(roleAdmin);
		Assertions.assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testOtherRoles() {
		Role sales= new Role("salesperson","manage product price");
		Role editor= new Role("editor","manage categories");
		Role shipper= new Role("shipper","view orders");
		Role assistent= new Role("assistent","manage reviews");
		repo.save(sales);
		repo.save(editor);
		repo.save(shipper);
		repo.save(assistent);
 		 
	}
	
}
