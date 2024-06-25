package com.onlineshop.mapper;

import org.springframework.stereotype.Component;

import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Seller;
import com.onlineshop.entity.User;
import com.onlineshop.enums.UserRole;
import com.onlineshop.requestdto.UserRequest;
import com.onlineshop.responsedto.UserResponse;

@Component
public class UserMapper {


	public User mapToUser(UserRequest userRequest, User user)
	{
		user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setEmailVerified(false);
        user.setDeletd(false);
        return user;
	}

	public UserResponse mapToUserResponse(User user)

	{
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.email(user.getEmail())
				.isDeletd(false)
				.isEmailVerified(false)
				.build();



	}

	
}
