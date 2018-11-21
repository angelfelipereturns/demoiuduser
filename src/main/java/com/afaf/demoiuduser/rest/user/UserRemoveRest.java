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
 * RestController class for removing a User
 *
 */
@RestController
public class UserRemoveRest {
	
	@Autowired
	private UserService userService;
	
	final static Logger logger = LoggerFactory.getLogger(UserUpdateRest.class);
	
	
	/**
	 * Method for removing a user, request DELETE, url: /apirest/users/removeUser/{id}
	 * @param id Integer
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value="/apirest/users/removeUser/{id}", method=RequestMethod.DELETE, produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> removeUser(@PathVariable Integer id) {
    	ResponseEntity<String> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	User userBD = null;
		try {
			userBD = userService.getById(id);
	        if (null!=userBD) {
	        	boolean success = userService.removeUser(userBD);
	        	if(success) {
	    			response = new ResponseEntity<>("user removed", HttpStatus.OK);
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
