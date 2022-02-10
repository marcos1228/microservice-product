package com.product.service;



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
