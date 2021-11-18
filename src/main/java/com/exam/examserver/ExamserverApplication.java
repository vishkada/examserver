package com.exam.examserver;

import com.exam.examserver.model.Role;
import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started Running..............");

		User user = User.builder().username("vishkada").firstName("Vishwas").lastName("Kadam")
				.email("vishwas.kadam9@gmail.com").password("test").phone("9158992222")
				.profile("Admin User").build();

		Role role = Role.builder().roleId(44L).roleName("Admin").build();

		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = UserRole.builder().role(role).user(user).build();
		userRoles.add(userRole);
		//User newUser = userService.createUser(user, userRoles);
		//System.out.println(newUser);
	}
}
