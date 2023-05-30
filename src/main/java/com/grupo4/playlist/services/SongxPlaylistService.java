package com.grupo4.playlist.services;

import java.util.List;
import java.util.UUID;

import com.grupo4.playlist.models.dtos.SaveSongDTO;
import com.grupo4.playlist.models.entities.Playlist;
import com.grupo4.playlist.models.entities.Song;

public interface SongxPlaylistService {
	List<Song> findAllbySong(Playlist playlist);
	List<Playlist> findAllbyPlaylist();
	void addSongToPlaylist(UUID songId, UUID playlistId);
		
}