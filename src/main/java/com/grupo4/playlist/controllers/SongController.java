package com.grupo4.playlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.playlist.models.dtos.MessageDTO;
import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.services.SongService;
import com.grupo4.playlist.utils.RequestErrorHandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/song")
public class SongController {
	@Autowired
	private SongService songService;
	
	@Autowired
	private RequestErrorHandler errorHandler;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAllBooks(){
		List<Song> songs = songService.findAll();
		return new ResponseEntity<>(
					songs,
					HttpStatus.OK
				);
	}
	
	@GetMapping("/{title}")
	public ResponseEntity<?> findOneByTitle(@PathVariable(name = "title") String title) {
		Song song = songService.findOneByTitle(title);
		
		if(song == null) {
			return new ResponseEntity<>(
					new MessageDTO("Song not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(song, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> save(@ModelAttribute @Valid SaveSongDTO info, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(
					errorHandler.mapErrors(validations.getFieldErrors()), 
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			songService.save(info);
			return new ResponseEntity<>(
					new MessageDTO("Song added"), HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{title}")
	public ResponseEntity<?> deleteByTitle(@PathVariable(name = "title") String title){
		try {
			songService.deleteByTitle(title);
			return new ResponseEntity<>(
					new MessageDTO("Song deleted"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
