//package com.product.service;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import com.product.domain.model.User;
//import com.product.repository.UserRepository;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService{
//	@Autowired
//	private UserRepository userRepository;
//	
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<User> user  = userRepository.findByLogin(username);
//		if (user.isEmpty()) {
//			throw new UsernameNotFoundException("");
//			
//		} 
//		return  new org.springframework.security.core.userdetails.User(user.get().getLogin(), user.get().getPassword(), getAuthority());
//	}
//    private List<SimpleGrantedAuthority> getAuthority() {
//        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }
//
//}
