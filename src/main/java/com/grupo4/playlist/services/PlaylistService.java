package com.grupo4.playlist.services;

import java.util.List;
import java.util.UUID;

import com.grupo4.playlist.models.dtos.ChangePlaylistDTO;
import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;

public interface PlaylistService {
	void save(SavePlaylistDTO info, User user) throws Exception;
	void deleteByTitle(String title) throws Exception;
	void updateByTitleAndUser(User user, ChangePlaylistDTO info) throws Exception;
	void delete(Playlist playlist);
	List<Playlist> findAll();
	Playlist findOneByTitle(String title);
}
