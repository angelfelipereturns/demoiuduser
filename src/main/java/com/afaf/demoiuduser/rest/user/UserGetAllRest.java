package com.afaf.demoiuduser.rest.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afaf.demoiuduser.data.model.User;
import com.afaf.demoiuduser.service.UserService;

/**
 * RestController class for getting all Users
 *
 */
@RestController
public class UserGetAllRest {
	
	@Autowired
	private UserService userService;
	
	final static Logger logger = LoggerFactory.getLogger(UserGetAllRest.class);
	
	
	/**
	 * Method for getting json of all users, request GET, url: /apirest/users/getAll
	 * @return ResponseEntity<List<User>>
	 */
	@RequestMapping(value="/apirest/users/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAll() {
		ResponseEntity<List<User>> response = ResponseEntity.noContent().build();
		List<User> list = null;
		try {
			list = userService.getAll();
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error(e.getMessage());
		}
		if (null!=list) {
			response = new ResponseEntity<>(list, HttpStatus.OK);
			
		}
        return response;
	}

}
