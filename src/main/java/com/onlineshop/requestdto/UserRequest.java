package com.onlineshop.requestdto;


import com.onlineshop.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	@NotNull(message="username cannot be null")
	@NotBlank(message="username cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name should only contain alphabetic characters")

    private String userName;
	
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should end with @gmail.com")	@NotNull(message="email cannot be null")
	@NotBlank(message="email cannot be null")
	
	
	private String email;
	
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
	         message = "Password must be at least 8 characters long, contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
	private String password;
	

}
