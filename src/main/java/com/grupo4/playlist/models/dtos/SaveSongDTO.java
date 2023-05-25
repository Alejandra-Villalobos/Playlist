package com.grupo4.playlist.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SaveSongDTO {
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private Integer duration;
}
