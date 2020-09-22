package com.mag.conduit.api.security;

import com.mag.conduit.application.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null
                && header.startsWith("Token ")
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<String> subject = jwtService.getSubFromToken(header.substring(6));
            if (subject.isPresent()) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        UUID.fromString(subject.get()),
                        null,
                        Collections.emptyList()
                );
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
