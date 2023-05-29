package com.grupo4.playlist.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.repositories.PlaylistRepository;
import com.grupo4.playlist.services.PlaylistService;

import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImp implements PlaylistService {
	@Autowired
	private PlaylistRepository playlistRepository;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(SavePlaylistDTO info, User user) throws Exception {
		Playlist playlist = new Playlist(
				info.getTitle(),
				info.getDescription(),
				user
			);
		playlistRepository.save(playlist);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByTitle(String title) throws Exception {
		playlistRepository.deleteByTitle(title);
		
	}

	/*@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteByTitleAndUser(String title, String user) throws Exception {
		playlistRepository.deleteByTitleAndUser(title, user);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void updateByTitleAndUser(String title, User user, SavePlaylistDTO info) throws Exception {
		playlistRepository.findOneByTitle(title);
		playlistRepository.deleteByTitle(title);
		Playlist playlist = new Playlist(
				info.getTitle(),
				info.getDescription(),
				user
			);
		playlistRepository.save(playlist);
		
	}*/

	@Override
	public List<Playlist> findAll() {
		List<Playlist> playlists = playlistRepository.findAll();
		return playlists;
	}

	@Override
	public Playlist findOneByTitle(String title) {
		Playlist playlist = playlistRepository.findOneByTitle(title);
		return playlist;
	}

	/*@Override
	public Playlist findOneByTitleAndUser(String title, String user) {
		Playlist playlist = playlistRepository.findOneByTitleAndUser(title, user);
		return playlist;
	}*/

}
