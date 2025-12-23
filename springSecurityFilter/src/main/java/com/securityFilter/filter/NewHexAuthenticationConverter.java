package com.securityFilter.filter;

import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

public class NewHexAuthenticationConverter implements AuthenticationConverter {
    @Override
    public @Nullable Authentication convert(HttpServletRequest request) {
        final var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Hex ")) {
            final var rawToken = authorization.replaceAll("^Hex ", "");
            final var token = rawToken; // new String(Hex.decode(rawToken), StandardCharsets.UTF_8); // Hex decoding
            final var tokenParts = token.split(":");

            return UsernamePasswordAuthenticationToken.unauthenticated(tokenParts[0], tokenParts[1]);
        }

        return null;
    }
}
