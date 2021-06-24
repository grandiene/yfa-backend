package com.kel3.yfaexpress.model.dto.keycloak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterKeycloakDto {

    private String firstName;

    private String lastName;

    private String email;

    private Boolean enabled;

    private String username;

    private List<CredentialsKeycloakDto> credentials;

}
