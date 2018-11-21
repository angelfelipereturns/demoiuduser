package com.afaf.demoiuduser.rest.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.afaf.demoiuduser.data.model.User;
import com.afaf.demoiuduser.service.UserService;

/**
 * RestController class for updating a User
 *
 */
@RestController
public class UserUpdateRest {
	
	@Autowired
	private UserService userService;
	
	final static Logger logger = LoggerFactory.getLogger(UserUpdateRest.class);
	
	
	/**
	 * Method Rest for updating a user, request PUT, url: /apirest/users/updateUser/{id}
	 * @param id Integer
	 * @param user User (example json on body: {"name":"angel", "birthdate":"1981-03-28T17:07:44.733+0000"}) 
	 * Note all attributes for good request
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/apirest/users/updateUser/{id}", method=RequestMethod.PUT, produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user) {
    	ResponseEntity<String> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	User userBD = null;
		try {
			userBD = userService.getById(id);
	        if (null!=userBD) {
	        	user.setId(id);
	        	boolean success = userService.saveUser(user);
	        	if(success) {
	    			response = new ResponseEntity<>("user updated", HttpStatus.OK);
	    		}
	        }
	        else {
	        	response = new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
	        }
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return response;
    }

}
