package com.grupo4.playlist.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveSongDTO {
	
	@NotEmpty(message = "Title can't be empty")
	private String title;
	
	@Positive(message = "Duration must be a positive number")
	private Integer duration;
}
