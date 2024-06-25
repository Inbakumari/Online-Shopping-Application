package com.onlineshop.service;

import org.springframework.http.ResponseEntity;

import com.onlineshop.enums.UserRole;
import com.onlineshop.requestdto.OTPVerificationRequest;
import com.onlineshop.requestdto.UserRequest;
import com.onlineshop.responsedto.UserResponse;
import com.onlineshop.utility.ResponseStructure;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest, UserRole userRole);

	ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OTPVerificationRequest otpVerificationRequest);


}
