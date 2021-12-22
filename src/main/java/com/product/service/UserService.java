package com.product.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.domain.dto.request.UserDtoRequest;
import com.product.domain.dto.request.UserUpdateDtoRequest;
import com.product.domain.dto.response.UserDtoResponse;
import com.product.domain.model.User;
import com.product.exception.BusinessException;
import com.product.repository.UserRepository;

@Service
public class UserService {
//	@Autowired
//	private ModelMapper modelMapper;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	public UserDtoResponse findByUser(Long id) {
//		User user = userRepository.findById(id).orElseThrow(() -> new BusinessException(""));
//		return modelMapper.map(user, UserDtoResponse.class);
//	}
//
//	@Transactional
//	public UserDtoResponse save(UserDtoRequest userDtoRequest) {
//		User user = modelMapper.map(userDtoRequest, User.class);
//		if (userRepository.findByLogin(userDtoRequest.getLogin()).isPresent()) {
//			throw new BusinessException("");
//		} else {
//			return modelMapper.map(userRepository.save(user), UserDtoResponse.class);
//		}
//	}
//
//	@Transactional
//	public UserDtoResponse update(Long id, UserUpdateDtoRequest userUpdateDtoRequest) {
//		User user = userRepository.findById(id).orElseThrow(() -> new BusinessException(""));
//		modelMapper.map(userUpdateDtoRequest, user);
//		return modelMapper.map(userRepository.save(user), UserDtoResponse.class);
//	}
//
//	@Transactional
//	public void delete(Long id) {
//		User user = userRepository.findById(id).orElseThrow(() -> new BusinessException(""));
//		userRepository.delete(user);
//	}
}
