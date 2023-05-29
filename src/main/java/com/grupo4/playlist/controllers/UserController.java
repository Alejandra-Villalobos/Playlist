package com.grupo4.playlist.controllers;

import java.util.UUID;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.playlist.models.dtos.ChangePasswordDTO;
import com.grupo4.playlist.models.dtos.LoginDTO;
import com.grupo4.playlist.models.dtos.MessageDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.services.UserService;
import com.grupo4.playlist.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid LoginDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.login(info);
			return new ResponseEntity<>(new MessageDTO("login OK!"), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"+info.getUsername()+info.getPassword()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@RequestBody @Valid SaveUserDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.saveUser(info);
			return new ResponseEntity<>(info, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"+info.getUsername()+info.getPassword()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<?> deleteByUsername(@PathVariable(name = "username") String username){
		if(userService.findOneByUsername(username) == null){
			return new ResponseEntity<>(new MessageDTO("Song not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new MessageDTO("User delete"), HttpStatus.OK);
	}
	
	@PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody @Valid String user, ChangePasswordDTO info, BindingResult validations){
		if(userService.findOneByUsername(user) == null){
			return new ResponseEntity<>(
					new MessageDTO("Username not found"), HttpStatus.NOT_FOUND);
		}
		else if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()),
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.changePassword(user, info);
			return new ResponseEntity<>(new MessageDTO("password update!"), HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Modificar este servicio porque debe mostrarse el UserInfoDTO sin la contraseña
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<User> users = userService.findAll(); 
		return new ResponseEntity<>(
				users,
				HttpStatus.OK
			);
	}
	
	@GetMapping("/get/{username}")
	public ResponseEntity<?> findOneByUsername(String username){
		return new ResponseEntity<>(
				"hola",
				HttpStatus.OK
			);
	}
}
