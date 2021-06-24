package com.kel3.yfaexpress.model.dto.keycloak;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeycloakDto {

    private String errorMessage;
    private String error;
    private String status;

}
