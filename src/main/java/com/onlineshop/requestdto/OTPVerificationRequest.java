package com.onlineshop.requestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class OTPVerificationRequest {
	

	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should end with @gmail.com")	@NotNull(message="email cannot be null")
	@NotBlank(message="email cannot be null")
	
	private String email;
	
	@NotNull(message = "OTP cannot be null")
	@NotEmpty(message = "OTP cannot be empty")
	@Pattern(regexp = "\\d{6}", message = "OTP must be exactly 6 numeric digits")
	private String otp;

}
