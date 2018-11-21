package com.afaf.demoiuduser.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afaf.demoiuduser.data.model.User;
import com.afaf.demoiuduser.service.UserService;

/**
 * RestController class for creating a User
 *
 */
@RestController
public class UserCreateRest {
	
	@Autowired
	private UserService userService;
	
	final static Logger logger = LoggerFactory.getLogger(UserCreateRest.class);
	
	
	/**
	 * Method for creating a user, request POST, url: /apirest/users/createUser
	 * @param user User (example json on body: {"name":"angel", "birthdate":"1981-03-28T17:07:44.733+0000"})
	 * Note all attributes for good request
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/apirest/users/createUser", method=RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> createUser(@RequestBody User user) {
		ResponseEntity<String> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		boolean success = false;
		try {
			success = userService.saveUser(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if(success) {
			response = new ResponseEntity<>("user created", HttpStatus.CREATED);
		}
        return response;
    }

}
