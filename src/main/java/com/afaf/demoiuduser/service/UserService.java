package com.afaf.demoiuduser.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.afaf.demoiuduser.data.UserRepository;
import com.afaf.demoiuduser.data.model.User;

/**
 * Service class for facade methods from User repository
 *
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * Method for getting all users
	 * @return List<User>
	 * @throws Exception 
	 */
	public List<User> getAll() throws Exception {
		try {
			return userRepository.findAll(Sort.by(Order.by("name")));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * Method for getting a user by id
	 * @param id Integer
	 * @return User
	 * @throws Exception 
	 */
	public User getById(final Integer id) throws Exception {
		User user = null;
		try {
			final Optional<User> optionalUser = userRepository.findById(id);
			if(optionalUser.isPresent()) {
				user = optionalUser.get();
			}
			return user;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * Method for getting a user by name
	 * @param name String
	 * @return User
	 * @throws Exception 
	 */
	public User getByName(String name) throws Exception {
		User user = null;
		try {
			final Optional<User> optionalUser = userRepository.findByName(name);
			if(optionalUser.isPresent()) {
				user = optionalUser.get();
			}
			return user;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * Method for getting a user by birthdate
	 * @param birthdate java.util.Date
	 * @return User
	 * @throws Exception 
	 */
	public User getByBirthdate(final Date birthdate) throws Exception {
		User user = null;
		try {
			final Optional<User> optionalUser = userRepository.findByBirthdate(birthdate);
			if(optionalUser.isPresent()) {
				user = optionalUser.get();
			}
			return user;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * Method for creating or update a user
	 * @param user User
	 * @return boolean success
	 * @throws Exception 
	 */
	public boolean saveUser(final User user) throws Exception {
		boolean success = true;
		try {
			final User userNew = userRepository.save(user);
			if(null==userNew) {
				success = false;
			}
			return success;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * Method for deleting a user
	 * @param user User
	 * @return boolean success
	 * @throws Exception 
	 */
	public boolean removeUser(final User user) throws Exception {
		try {
			userRepository.delete(user);
		} catch (Exception e) {
			throw new Exception(e);
		}
		return true;
	}
	
	

}
