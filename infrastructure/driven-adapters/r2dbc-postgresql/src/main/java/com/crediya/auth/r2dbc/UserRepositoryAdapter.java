package com.crediya.auth.r2dbc;

import com.crediya.auth.model.user.User;
import com.crediya.auth.model.user.exceptions.EmailAlreadyExistsException;
import com.crediya.auth.model.user.exceptions.UserNotFoundException;
import com.crediya.auth.model.user.gateways.UserRepository;
import com.crediya.auth.r2dbc.entity.UserEntity;
import com.crediya.auth.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        String,
        UserReactiveRepository
> implements UserRepository {
    public UserRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, entity -> mapper.map(entity, User.class));
    }

    @Override
    public Mono<User> save(User user) {
        return super.save(user)
                .onErrorMap(DuplicateKeyException.class,
                        ex -> new EmailAlreadyExistsException(user.getEmail()));
    }

    @Override
    public Mono<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .switchIfEmpty(Mono.error(() -> new UserNotFoundException(email)));
    }
}
