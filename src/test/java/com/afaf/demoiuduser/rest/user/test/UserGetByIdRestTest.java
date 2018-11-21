package com.afaf.demoiuduser.rest.user.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.afaf.demoiuduser.data.model.User;
import com.afaf.demoiuduser.rest.user.UserGetByIdRest;
import com.afaf.demoiuduser.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserGetByIdRest.class)
public class UserGetByIdRestTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	final User mockUser = new User();
	
	
	@Test
	public void getUserById() throws Exception {
		mockUser.setId(97);
		mockUser.setName("Angel");
		mockUser.setBirthdate(new Date(new Long("354647220000")));

		Mockito.when(userService.getById(mockUser.getId())).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/apirest/users/getById/"+mockUser.getId())
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{\"id\":97,\"name\":\"Angel\",\"birthdate\":\"1981-03-28T17:07:00.000+0000\"}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
