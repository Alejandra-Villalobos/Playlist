package com.grupo4.playlist.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
	
	@NotEmpty(message = "Username can't be empty")
	private String username;
	
	@NotEmpty(message = "Password can't be empty")
	@Size(min = 5, message = "Password must have a minimum of 5 characters")
	private String password;
}
