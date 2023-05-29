package com.grupo4.playlist.services;

import java.util.List;

import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;

public interface SongxPlaylistService {
	List<Song> findAllbySong();
	List<Playlist> findAllbyPlaylist();
		
}