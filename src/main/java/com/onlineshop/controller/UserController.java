package com.onlineshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.enums.UserRole;
import com.onlineshop.requestdto.OTPVerificationRequest;
import com.onlineshop.requestdto.UserRequest;
import com.onlineshop.responsedto.UserResponse;
import com.onlineshop.service.UserService;
import com.onlineshop.utility.ErrorStructure;
import com.onlineshop.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@Tag(name="User Endpoints",description="Contains all the endpoints")
@AllArgsConstructor
public class UserController {
	
	private UserService userService;
	
	@Operation(description="The endpoint is used to add the"
			+ " data to the data base",
			responses= {
					@ApiResponse(responseCode = "201", description="User created successfully",
							content= {
									@Content(schema = @Schema(oneOf=UserResponse.class))
							}),
					@ApiResponse(responseCode="400", description="Invalid Input",
					content= {
							@Content(schema  =@Schema(oneOf  =ErrorStructure.class))
					})
			})
	
	@PostMapping("/sellers/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveSeller(@RequestBody UserRequest userRequest) {
		return userService.saveUser(userRequest, UserRole.SELLER);
	}
	
	@PostMapping("/customers/register")
	public ResponseEntity<ResponseStructure<UserResponse>> saveCustomer(@RequestBody UserRequest userRequest) {
		return userService.saveUser(userRequest, UserRole.CUSTOMER);
	}
	
	@PostMapping("/verify")
	public ResponseEntity<ResponseStructure<UserResponse>>  verifyOTP(@RequestBody @Valid OTPVerificationRequest otpVerificationRequest)
	{
		return userService.verifyOTP(otpVerificationRequest);
	}
}