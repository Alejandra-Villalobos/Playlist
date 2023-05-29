package com.grupo4.playlist.repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.grupo4.playlist.models.entities.Playlist;

public interface PlaylistRepository extends ListCrudRepository<Playlist, UUID> {
	void deleteByTitle(String title);
	void delete(Playlist playlist);
	Playlist findOneByTitle(String title);
}
