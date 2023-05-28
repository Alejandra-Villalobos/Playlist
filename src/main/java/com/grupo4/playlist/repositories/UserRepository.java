package com.grupo4.playlist.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.grupo4.playlist.models.dtos.ChangePasswordDTO;
import com.grupo4.playlist.models.dtos.LoginDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.dtos.UserInfoDTO;
import com.grupo4.playlist.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID> {
	void login(LoginDTO login) throws Exception;
	void deleteByCode(UUID code) throws Exception;
	void deleteByUsername(String user) throws Exception;
	void changePassword(String user, ChangePasswordDTO info) throws Exception;
	User findOneByUsername(String username);
	List<UserInfoDTO> findAllUser();
}