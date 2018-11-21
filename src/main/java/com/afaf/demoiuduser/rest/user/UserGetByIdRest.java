package com.afaf.demoiuduser.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afaf.demoiuduser.data.model.User;
import com.afaf.demoiuduser.service.UserService;

/**
 * RestController class for getting a User by id
 *
 */
@RestController
public class UserGetByIdRest {
	
	@Autowired
	private UserService userService;
	
	final static Logger logger = LoggerFactory.getLogger(UserGetByIdRest.class);
	
	
	/**
	 * Method for getting json of a user by id, request GET, url: /apirest/users/getById/{id}
	 * @return ResponseEntity<User>
	 */
	@RequestMapping(value="/apirest/users/getById/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getById(@PathVariable Integer id) {
		ResponseEntity<User> response = ResponseEntity.noContent().build();
		User user = null;
		try {
			user = userService.getById(id);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			logger.error(e.getMessage());
		}
		if (null!=user) {
			response = new ResponseEntity<>(user, HttpStatus.OK);
			
		}
        return response;
	}

}
