package com.amalitec.amalitececom.service;

import com.amalitec.amalitececom.model.User;
import com.amalitec.amalitececom.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	 private final UserRepository userRepository;


	    @Override
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    @Override
	    public Optional<User> getUserById(UUID id) {
	        return userRepository.findById(id);
	    }

	    @Override
	    public User createUser(User user) {
	        return userRepository.save(user);
	    }

	    @Override
	    public User updateUser(UUID id, User user) {
	        if (userRepository.existsById(id)) {
	            user.setId(id);
	            return userRepository.save(user);
	        } else {
	            return null;
	        }
	    }

	    @Override
	    public boolean deleteUser(UUID id) {
	        if (userRepository.existsById(id)) {
	            userRepository.deleteById(id);
	            return true;
	        } else {
	            return false;
	        }
	    }

	@Override
	public boolean existsById(UUID id) {
		return userRepository.existsById(id);
	}

	@Override
	public Optional<User> getByEmail(String email) {
		return userRepository.findByEmail(email);
	}



}

