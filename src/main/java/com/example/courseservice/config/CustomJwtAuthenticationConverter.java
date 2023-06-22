package com.example.courseservice.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {


    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        System.out.println(jwt);

        // Extract the "roles" claim from the JWT
        Collection<String> permissions = jwt.getClaim("authorities");

        System.out.println(permissions);

        // Map the roles to SimpleGrantedAuthority objects
        Collection<GrantedAuthority> authorities = permissions.stream().map(permission ->
                new SimpleGrantedAuthority(permission)
        ).collect(Collectors.toList());


        // Create an authentication token with the JWT and authorities
        return new JwtAuthenticationToken(jwt, authorities);
    }
}
