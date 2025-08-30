package com.interior.archiThink.service;

import com.interior.archiThink.config.JWTService;
import com.interior.archiThink.dto.UserDto;
import com.interior.archiThink.mapper.UserMapper;
import com.interior.archiThink.model.Role;
import com.interior.archiThink.model.User;
import com.interior.archiThink.model.UserPrincipal;
import com.interior.archiThink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private JWTService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException("User " + username + " not found");
    }

    @Override
    public UserDto register(UserDto userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userMapper.toEntity(userDTO);
        savedUser.setRole(Role.USER);
        userRepository.save(savedUser);
        return userDTO;
    }

    @Override
    public String verify(UserDto userDTO) {
        AuthenticationManager authManager = context.getBean(AuthenticationManager.class);
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDTO.getUsername());
        }
        return null;
    }

    @Override
    public UserDto getUser(Long UserId) {
        User User = userRepository.findById(UserId).orElse(null);
        return userMapper.toDTO(User);
    }

    @Override
    public List<UserDto> getUserList() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto UserDto, Long UserId) {
        User User = userRepository.findById(UserId).orElse(null);
        if(User == null)
            return null;
        userMapper.update(UserDto, User);
        userRepository.save(User);
        return UserDto;

    }

    @Override
    public Boolean deleteUserById(Long id) {
        User User = userRepository.findById(id).orElse(null);
        if (User == null)
            return Boolean.FALSE;
        userRepository.delete(User);
        return Boolean.TRUE;
    }
}
