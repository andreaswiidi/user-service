package com.andreaswidii.user.service;

import com.andreaswidii.user.beans.AuthJWTResponse;
import com.andreaswidii.user.beans.AuthReq;
import com.andreaswidii.user.exception.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
    public AuthJWTResponse getJWTFromAuthService(AuthReq authReq){
        //TODO : Change using microservice url
        return WebClient.create("http://localhost:8081")
                .post()
                .uri("/v1/get-jwt")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(authReq), AuthReq.class)
                .retrieve()
                .bodyToMono(AuthJWTResponse.class)
                .onErrorMap(e -> {
                    throw new BadRequestException("Error Get JWT");
                })
                .block();
    }
}
