package ru.netology.diplom.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SecurityResponse {
    @JsonProperty("auth-token")
    private String authToken;

}
