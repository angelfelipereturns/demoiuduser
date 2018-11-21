package com.afaf.demoiuduser.data;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.afaf.demoiuduser.data.model.User;

/**
 * Repository inteface for JPA methods on User
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Method for finding a user by name
	 * @param name String
	 * @return Optional<User>
	 */
	public Optional<User> findByName(String name);
	
	/**
	 * Method for finding a user by birthdate
	 * @param birthdate java.util.Date
	 * @return Optional<User>
	 */
	public Optional<User> findByBirthdate(Date birthdate);

}
