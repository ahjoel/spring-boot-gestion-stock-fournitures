package com.gestock.fourniture.service.security;

import com.gestock.fourniture.exceptions.CustomException;
import com.gestock.fourniture.model.Role;
import com.gestock.fourniture.model.dto.FournitureDto;
import com.gestock.fourniture.model.dto.request.SignUpRequest;
import com.gestock.fourniture.model.dto.request.SigninRequest;
import com.gestock.fourniture.model.dto.response.JwtAuthenticationResponse;
import com.gestock.fourniture.model.entities.User;
import com.gestock.fourniture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstName(request.getFirstName()).pseudo(request.getPseudo())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        checkPseudoAlreadyUsed(request);
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    private void checkPseudoAlreadyUsed(SignUpRequest request) {
        if (userRepository.existsByPseudoIgnoreCase(request.getPseudo())){
            throw new CustomException("Code 5268", "Il existe déjà un utilisateur avec ce pseudo", HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPseudo(), request.getPassword()));

            var user = userRepository.findByPseudo(request.getPseudo())
                    .orElseThrow(() -> new CustomException("Code 257", "Le pseudo et le mot de passe sont incorrectes", HttpStatus.FORBIDDEN));
            var jwt = jwtService.generateToken(user);
            return JwtAuthenticationResponse.builder().token(jwt).build();

        } catch (AuthenticationException ex) {
            throw new CustomException("Code 257", "Le pseudo et le mot de passe sont incorrectes", HttpStatus.UNAUTHORIZED);
        }

    }
}