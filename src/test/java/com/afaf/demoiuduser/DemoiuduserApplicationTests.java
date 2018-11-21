package com.afaf.demoiuduser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.afaf.demoiuduser.rest.user.UserCreateRest;
import com.afaf.demoiuduser.rest.user.UserGetAllRest;
import com.afaf.demoiuduser.rest.user.UserGetByIdRest;
import com.afaf.demoiuduser.rest.user.UserRemoveRest;
import com.afaf.demoiuduser.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoiuduserApplicationTests {
	
	@Autowired
	private UserGetAllRest userGetAllRest;
	
	@Autowired
	private UserGetByIdRest userGetByIdRest;
	
	@Autowired
	private UserCreateRest userCreateRest;
	
	@Autowired
	private UserRemoveRest userRemoveRest;
	
	@Autowired
	private UserService userService;
	
	
	@Test
	public void contextLoads() {
		assertThat(userGetAllRest).isNotNull();
		assertThat(userGetByIdRest).isNotNull();
		assertThat(userCreateRest).isNotNull();
		assertThat(userRemoveRest).isNotNull();
		assertThat(userService).isNotNull();
	}

}
