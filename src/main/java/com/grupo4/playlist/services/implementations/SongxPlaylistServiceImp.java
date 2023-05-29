package com.grupo4.playlist.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo4.playlist.models.dtos.SavePlaylistDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;
import com.grupo4.playlist.models.entities.User;
import com.grupo4.playlist.repositories.PlaylistRepository;
import com.grupo4.playlist.repositories.SongRepository;
import com.grupo4.playlist.services.PlaylistService;
import com.grupo4.playlist.services.SongxPlaylistService;

import jakarta.transaction.Transactional;

@Service
public class SongxPlaylistServiceImp implements SongxPlaylistService {

	@Override
	public List<Song> findAllbySong() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Playlist> findAllbyPlaylist() {
		// TODO Auto-generated method stub
		return null;
	}

}