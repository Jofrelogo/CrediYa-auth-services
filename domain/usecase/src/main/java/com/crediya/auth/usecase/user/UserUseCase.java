package com.crediya.auth.usecase.user;

import com.crediya.auth.model.jwt.PasswordHashProvider;
import com.crediya.auth.model.user.User;
import com.crediya.auth.model.user.exceptions.UserNotFoundException;
import com.crediya.auth.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final PasswordHashProvider passwordHashProvider;

    public Mono<User> saveUser(User user){

        String encodedPassword = passwordHashProvider.encode(user.getPasswordHash());
        user.setPasswordHash(encodedPassword);

        return userRepository.save(user);
    }

    public Mono<User> findByEmail(String email){
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new UserNotFoundException(email)));
    }

}
