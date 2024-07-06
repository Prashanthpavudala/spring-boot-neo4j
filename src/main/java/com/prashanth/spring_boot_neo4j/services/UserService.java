package com.prashanth.spring_boot_neo4j.services;

import com.prashanth.spring_boot_neo4j.dto.UserDTO;
import com.prashanth.spring_boot_neo4j.models.User;
import com.prashanth.spring_boot_neo4j.repositories.UserRepository;
import com.prashanth.spring_boot_neo4j.requests.CreateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> registerNewUser(CreateUserRequest request) {
        log.info("Inside registerNewUser with request {}", request);
        Optional<User> existingUser = userRepository.findByUsername(request.getUsername());
        if(existingUser.isPresent()){
            return new ResponseEntity<>("User already exists!!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(request.getRoles());
        userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
