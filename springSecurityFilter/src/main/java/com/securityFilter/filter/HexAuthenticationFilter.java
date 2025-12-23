package com.securityFilter.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class HexAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    private final SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    private final AuthenticationManager authManager;

    private final AuthenticationEntryPoint entryPoint;

    public HexAuthenticationFilter(AuthenticationManager authManager, AuthenticationEntryPoint entryPoint) {
        this.authManager = authManager;
        this.entryPoint = entryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.startsWith("Hex ")) {
            final var rawToken = authorization.replaceAll("^Hex ", "");
            final var token = rawToken; // new String(Hex.decode(rawToken), StandardCharsets.UTF_8); // Hex decoding
            final var tokenParts = token.split(":");

            final var authRequest = UsernamePasswordAuthenticationToken.unauthenticated(tokenParts[0], tokenParts[1]);

            try {
                final var authResult = authManager.authenticate(authRequest);
                final var context = securityContextHolderStrategy.createEmptyContext();
                context.setAuthentication(authResult);
                securityContextHolderStrategy.setContext(context);
                securityContextRepository.saveContext(context, request, response);

            } catch (AuthenticationException e) {
                securityContextHolderStrategy.clearContext();
                entryPoint.commence(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
