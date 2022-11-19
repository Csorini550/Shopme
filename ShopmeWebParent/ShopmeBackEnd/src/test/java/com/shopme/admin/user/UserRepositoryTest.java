package com.shopme.admin.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository repository;
	@Autowired
	private TestEntityManager em;
	@Test
	public void createUser() {
		Role adminRole= em.find(Role.class, 1);
		User userAmar= new User("amarjeet", "singh", "amar@mail.com","amar@123");
		userAmar.addRole(adminRole);
		User savedUser=repository.save(userAmar);
		Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
		
	}
	
	
	
	@Test
	public void createUserWith2Roles() {
		User userRavi= new User("ravi", "kumar", "ravi@mail.com","ravi@123");

		Role editor= em.find(Role.class, 3);
		Role assis= em.find(Role.class, 5);
		 
		userRavi.addRole(assis);
		userRavi.addRole(editor);
		User saveduser=repository.save(userRavi);
		Assertions.assertThat(saveduser.getId()).isGreaterThan(0);
		
		 
		
	}


}
