package com.amalitec.amalitececom.service;

import com.amalitec.amalitececom.model.User;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

	List<User> getAllUsers();
	Optional<User> getUserById(UUID id);
	User createUser(User user);
	User updateUser(UUID id, User user);
	boolean deleteUser(UUID id);

    boolean existsById(UUID id);

	Optional<User> getByEmail(String email);
}
