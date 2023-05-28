package com.grupo4.playlist.services.implementations;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.grupo4.playlist.models.dtos.ChangePasswordDTO;
import com.grupo4.playlist.models.dtos.LoginDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.dtos.UserInfoDTO;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.repositories.UserRepository;
import com.grupo4.playlist.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void login(LoginDTO login) throws Exception {
		User user = userRepository.findOneByUsername(login.getUsername());
		
		if(user == null) {
			throw new Exception("User not found");
		}
		
		if(!user.getPassword().equals(login.getPassword())) {
			throw new Exception("Invalid credentials");
		}
		
		throw new ResponseStatusException(HttpStatus.OK,"Login OK!");
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SaveUserDTO info) throws Exception {
		try {
			User newUser = new User(info.getUsername(), info.getPassword(), info.getEmail());
			userRepository.save(newUser);
		}catch(Exception e){
			throw new Exception("Error save user");
		}
		
		throw new ResponseStatusException(HttpStatus.OK,"user created!");
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByCode(UUID code) throws Exception {
		userRepository.deleteByCode(code);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByUsername(String user) throws Exception {
		userRepository.deleteByUsername(user);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void changePassword(String username, ChangePasswordDTO info) throws Exception {
		User user = userRepository.findOneByUsername(username);

        if (user == null || !user.getPassword().equals(info.getCurrentPassword())) {
            throw new InvalidParameterException("Invalid current password");
        }
        user.setPassword(info.getNewPassword());
        userRepository.save(user);
        
        throw new ResponseStatusException(HttpStatus.OK,"password change succesfull!");
		
	}

	@Override
	public List<UserInfoDTO> findAll() {
		return userRepository.findAllUser();
	}

	@Override
	public User findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}
	
	
}
