package com.afaf.demoiuduser.rest.user.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.afaf.demoiuduser.data.model.User;
import com.afaf.demoiuduser.rest.user.UserCreateRest;
import com.afaf.demoiuduser.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserCreateRest.class)
public class UserCreateRestTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	final User mockUser = new User();
	final String userJson = "{\"name\":\"Test\",\"birthdate\":\"2018-11-19T07:06:00.000+0000\"}";
	
	
	@Test
	public void createUser() throws Exception {
		mockUser.setName("Test");
		mockUser.setBirthdate(new Date(new Long("1542607560000")));

		Mockito.when(userService.saveUser(mockUser)).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/apirest/users/createUser")
				.accept(MediaType.TEXT_PLAIN).content(userJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

}
