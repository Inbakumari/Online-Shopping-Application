package com.onlineshop.serviceimpl;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.onlineshop.entity.Customer;
import com.onlineshop.entity.Seller;
import com.onlineshop.entity.User;
import com.onlineshop.enums.UserRole;
import com.onlineshop.mailservice.MailService;
import com.onlineshop.mapper.UserMapper;
import com.onlineshop.repository.CustomerRepository;
import com.onlineshop.repository.SellerRepository;
import com.onlineshop.repository.UserRepository;
import com.onlineshop.requestdto.OTPVerificationRequest;
import com.onlineshop.requestdto.UserRequest;
import com.onlineshop.responsedto.UserResponse;
import com.onlineshop.service.UserService;
import com.onlineshop.utility.MessageData;
import com.onlineshop.utility.ResponseStructure;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpln implements UserService {



	private UserRepository userRepository;

	private UserMapper userMapper;

	private SellerRepository sellerRepository;

	private CustomerRepository customerRepository;

	private final Cache<String, User> userCache;

	private final Cache<String,String> otpCache;

	private final Random random;

	private final MailService mailService;





	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest, UserRole userRole) {

		User user=null;


		switch (userRole)
		{
		case SELLER -> user = new User();
		case CUSTOMER -> user = new Customer();
		}

		user=userMapper.mapToUser(userRequest, user);		
		userCache.put(user.getEmail(), user);
		userCache.getIfPresent(user.getEmail());
		otpCache.getIfPresent(user.getEmail());
		otpCache.put(user.getEmail(), String.valueOf(random.nextInt(100000, 999999)));
		user.setUserRole(userRole);
		user=userRepository.save(user);

		MessageData message=new MessageData();
		message.setTo(user.getEmail());
		message.setSubject("OTP Verification");
		message.setText("Your OTP is: "+otpCache.getIfPresent(user.getEmail()));
		message.setSentDate(new Date(System.currentTimeMillis()));


		try
		{
			mailService.sendMail(message);

		}
		catch (MessagingException e)

		{
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ResponseStructure<UserResponse>()
						.setStatus(HttpStatus.ACCEPTED.value())
						.setData(userMapper.mapToUserResponse(user))
						.setMessage("User Created"));
	}




	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OTPVerificationRequest otpVerificationRequest) {


		System.out.println(otpVerificationRequest.getEmail());
		System.out.println(userCache.getIfPresent(otpVerificationRequest.getEmail()));

		System.out.println(otpCache.getIfPresent(otpVerificationRequest.getEmail()));

		return null;
	}


}



