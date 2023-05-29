package com.grupo4.playlist.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ChangePlaylistDTO {
	@NotEmpty(message = "Old title can't be empty")
	private String Oldtitle;
	
	@NotEmpty(message = "Title can't be empty")
	private String title;

	@NotEmpty(message = "Description can't be empty")
	private String description;
	
	@NotEmpty(message = "User can't be empty")
	private String user;

}
