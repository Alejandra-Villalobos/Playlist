package com.grupo4.playlist.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.services.SongService;

@RestController
@RequestMapping("/song")
public class SongController {
	@Autowired
	private SongService songService;
	
	@GetMapping("all")
	public ResponseEntity<?> findAllBooks(){
		List<Song> songs = songService.findAll();
		return new ResponseEntity<>(
					songs,
					HttpStatus.OK
				);
	}

}
