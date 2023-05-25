package com.grupo4.playlist.services;

import java.util.List;

import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Song;

public interface SongService {
	void save(SaveSongDTO song) throws Exception;
	void deleteByTitle(String title) throws Exception;
	void update(SaveSongDTO song) throws Exception;
	List<Song> findAll();
	Song findOneByTitle(String title);
	
	
}
