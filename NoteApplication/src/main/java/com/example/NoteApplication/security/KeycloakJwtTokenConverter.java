package com.example.NoteApplication.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakJwtTokenConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final KeycloakJwtAuthoritiesConverter authoritiesConverter;

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        return new JwtAuthenticationToken(source, authoritiesConverter.convert(source), getPrincipalClaimName(source));
    }

    private String getPrincipalClaimName(Jwt source){
        return source.getClaim("preferred_username");
    }
}
