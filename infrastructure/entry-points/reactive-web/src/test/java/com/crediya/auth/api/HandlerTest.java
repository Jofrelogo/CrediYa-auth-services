package com.crediya.auth.api;

import com.crediya.auth.api.config.UserPath;
import com.crediya.auth.api.dto.UserRequestDTO;
import com.crediya.auth.model.user.User;
import com.crediya.auth.usecase.user.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

class HandlerTest {

    private WebTestClient webTestClient;
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        userUseCase = Mockito.mock(UserUseCase.class);
        Handler handler = new Handler(userUseCase, null);

        UserPath userPath = new UserPath();
        RouterRest routerRest = new RouterRest(userPath);

        webTestClient = WebTestClient.bindToRouterFunction(routerRest.routerFunction(handler)).build();
    }

    /*@Test
    void testCreateUser() {
        UserRequestDTO request = new UserRequestDTO();
        request.setDni("123456789");
        request.setFirstName("Carlos");
        request.setLastName("Lopez");
        request.setEmail("carlos@uno.com");
        request.setBirthDate(LocalDate.of(1990, 5, 15));
        request.setAddress("Calle 123");
        request.setPhoneNumber("3001234567");
        request.setBaseSalary(2000.0);

        User savedUser = User.builder()
                .dni("123456789")
                .firstName("Carlos")
                .lastName("Lopez")
                .email("carlos@uno.com")
                .build();

        Mockito.when(userUseCase.saveUser(Mockito.any(User.class))).thenReturn(Mono.just(savedUser));

        webTestClient.post()
                .uri("/api/v1/createUsers")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("Carlos")
                .jsonPath("$.email").isEqualTo("carlos@uno.com");
    }

    @Test
    void testGetUserByEmail() {
        User user = User.builder()
                .dni("123456789")
                .firstName("Carlos")
                .lastName("Lopez")
                .email("carlos@uno.com")
                .build();

        Mockito.when(userUseCase.findByEmail("carlos@uno.com")).thenReturn(Mono.just(user));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/users/by-email")
                        .queryParam("email", "carlos@uno.com")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.email").isEqualTo("carlos@uno.com");
    }*/
}
