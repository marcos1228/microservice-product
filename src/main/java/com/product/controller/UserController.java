//package com.product.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.product.domain.dto.request.UserDtoRequest;
//import com.product.domain.dto.response.UserDtoResponse;
//import com.product.util.JwTokenUtil;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//	
////	@Autowired
////	private UserService userService;
////	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private JwTokenUtil jwTokenUtil;
//
//	@PostMapping("/login")
//	public ResponseEntity<UserDtoResponse> getByUser(@RequestBody UserDtoRequest userDtoRequest) {
//		UserDtoResponse dtoResponse = new UserDtoResponse();
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(userDtoRequest.getLogin(), userDtoRequest.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		if (authentication.isAuthenticated()) {
//			String token = jwTokenUtil.generateToken(userDtoRequest.getLogin());
//			UserDtoResponse user = UserDtoResponse.builder().login(userDtoRequest.getLogin()).token(token).build();
//			return ResponseEntity.ok().body(user);
//		}
//		return ResponseEntity.ok().body(dtoResponse);
//	}
//	
////	public ResponseEntity<User> principal(Principal principal) {
////		userService.
////		
////	}
////	
//}
