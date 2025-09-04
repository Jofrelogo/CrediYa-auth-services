package com.crediya.auth.api;

import com.crediya.auth.api.config.UserPath;
import com.crediya.auth.api.dto.UserRequestDTO;
import com.crediya.auth.api.dto.UserResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to user management")
public class RouterRest {

    private final UserPath userPath;


    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/users",
                    produces = { "application/json" },
                    method = { org.springframework.web.bind.annotation.RequestMethod.POST },
                    beanClass = Handler.class,
                    beanMethod = "createUser",
                    operation = @Operation(
                            operationId = "createUser",
                            summary = "Create new user",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    content = @Content(schema = @Schema(implementation = UserRequestDTO.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "User created",
                                            content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/users/by-email",
                    produces = { "application/json" },
                    method = { org.springframework.web.bind.annotation.RequestMethod.GET },
                    beanClass = Handler.class,
                    beanMethod = "getUserByEmail",
                    operation = @Operation(
                            operationId = "getUserByEmail",
                            summary = "Find user by email",
                            parameters = {
                                    @Parameter(in = ParameterIn.QUERY, name = "email", required = true,
                                            description = "Email of the user to fetch")
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "User found",
                                            content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
                                    @ApiResponse(responseCode = "404", description = "User not found")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return RouterFunctions.route()
                .POST(userPath.getUsers(), handler::createUser)
                .GET(userPath.getFindByEmail(), handler::getUserByEmail)
                .build();

    }
}
