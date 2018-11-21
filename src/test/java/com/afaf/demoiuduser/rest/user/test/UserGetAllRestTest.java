package com.afaf.demoiuduser.rest.user.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.afaf.demoiuduser.rest.user.UserGetAllRest;
import com.afaf.demoiuduser.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserGetAllRest.class)
public class UserGetAllRestTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	final List<User> listaUsers = new ArrayList<User>();
	final User mockUser1 = new User();
	final User mockUser2 = new User();
	
	
	@Test
	public void getAllUsers() throws Exception {
		mockUser1.setId(97);
		mockUser1.setName("Angel");
		mockUser1.setBirthdate(new Date(new Long("354647220000")));
		listaUsers.add(mockUser1);
		mockUser2.setId(98);
		mockUser2.setName("Pepe");
		mockUser2.setBirthdate(new Date(new Long("274406160000")));
		listaUsers.add(mockUser2);
		
		Mockito.when(userService.getAll()).thenReturn(listaUsers);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/apirest/users/getAll").accept(
				MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"id\":97,\"name\":\"Angel\",\"birthdate\":\"1981-03-28T17:07:00.000+0000\"},"+
				"{\"id\":98,\"name\":\"Pepe\",\"birthdate\":\"1978-09-11T23:56:00.000+0000\"}]";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
