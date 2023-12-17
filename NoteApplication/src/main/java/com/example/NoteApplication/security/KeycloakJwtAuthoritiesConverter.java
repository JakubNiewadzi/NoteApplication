package com.example.NoteApplication.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.NoteApplication.security.constants.JwtConstants.REALM_ACCESS;
import static com.example.NoteApplication.security.constants.JwtConstants.RESOURCE_ACCESS;
import static com.example.NoteApplication.security.constants.JwtConstants.ROLES;
import static com.example.NoteApplication.security.constants.JwtConstants.ROLE_PREFIX;

@Component
@RequiredArgsConstructor
public class KeycloakJwtAuthoritiesConverter implements Converter<Jwt, Collection<? extends GrantedAuthority>> {

    @Value("${app.jwt.resource-client-id}")
    private String clientId;

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    @Override
    public Collection<? extends GrantedAuthority> convert(@NonNull Jwt source) {
        Stream<SimpleGrantedAuthority> roles = getRoles(source);
        Stream<GrantedAuthority> scopeAccess = getScopeAccess(source);

        return Stream.concat(roles, scopeAccess)
                .collect(Collectors.toSet());
    }

    private Stream<SimpleGrantedAuthority> getRoles(Jwt source) {
        Stream<SimpleGrantedAuthority> realmAccess = getRealmAccess(source);
        Stream<SimpleGrantedAuthority> resourceAccess = getResourceAccess(source);
        return Stream.concat(realmAccess, resourceAccess);
    }

    @SuppressWarnings("unchecked")
    private Stream<SimpleGrantedAuthority> getRealmAccess(Jwt source) {
        return Optional.of(source)
                .map(token -> token.getClaimAsMap(REALM_ACCESS))
                .map(realmData -> (Collection<String>) realmData.get(ROLES))
                .stream()
                .flatMap(Collection::stream)
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .distinct();
    }

    @SuppressWarnings("unchecked")
    private Stream<SimpleGrantedAuthority> getResourceAccess(Jwt source) {
        return Optional.of(source)
                .map(token -> token.getClaimAsMap(RESOURCE_ACCESS))
                .map(claimMap -> (Map<String, Object>) claimMap.get(clientId))
                .map(resourceData -> (Collection<String>) resourceData.get(ROLES))
                .stream()
                .flatMap(Collection::stream)
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .distinct();
    }

    private Stream<GrantedAuthority> getScopeAccess(Jwt source) {
        return jwtGrantedAuthoritiesConverter.convert(source)
                .stream();
    }
}
