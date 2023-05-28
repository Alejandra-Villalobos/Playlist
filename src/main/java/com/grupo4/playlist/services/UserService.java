package com.grupo4.playlist.services;

import java.util.List;
import java.util.UUID;

import com.grupo4.playlist.models.dtos.ChangePasswordDTO;
import com.grupo4.playlist.models.dtos.LoginDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.dtos.UserInfoDTO;
import com.grupo4.playlist.models.entities.User;

public interface UserService {
	
	void login(LoginDTO login) throws Exception;
	void save(SaveUserDTO info) throws Exception;
	void deleteByCode(UUID code) throws Exception;
	void deleteByUsername(String user) throws Exception;
	void changePassword(String user, ChangePasswordDTO info) throws Exception;
	List<UserInfoDTO> findAll();
	User findOneByUsername(String username);
}
