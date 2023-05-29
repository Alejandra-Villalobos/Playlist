package com.grupo4.playlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.playlist.models.dtos.MessageDTO;
import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.dtos.SaveUserDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.services.PlaylistService;
import com.grupo4.playlist.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAllBooks(){
		List<Playlist> playlists = playlistService.findAll();
		return new ResponseEntity<>(
					playlists,
					HttpStatus.OK
				);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<?> findOneByTitle(@PathVariable(name = "title") String title) {
		Playlist playlist = playlistService.findOneByTitle(title);
		
		if(playlist == null) {
			return new ResponseEntity<>(
					new MessageDTO("Playlist not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(playlist, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> savePlaylist(@RequestBody @Valid SavePlaylistDTO info, @RequestBody @Valid User user, BindingResult validations){
		if(validations.hasErrors()) {
			System.out.println(info.getTitle());
			System.out.println(info.getDescription());
			System.out.println(user);
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			playlistService.save(info, user);
			return new ResponseEntity<>(new MessageDTO("Playlist created!"), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{title}")
	public ResponseEntity<?> deleteByTitle(@PathVariable(name = "title") String title){
		if(playlistService.findOneByTitle(title) == null){
			return new ResponseEntity<>(new MessageDTO("Playlist not found"), HttpStatus.NOT_FOUND);
		}
		
		try {
			playlistService.deleteByTitle(title);
			return new ResponseEntity<>(new MessageDTO("playlist deleted"), HttpStatus.OK);
		}catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
}

