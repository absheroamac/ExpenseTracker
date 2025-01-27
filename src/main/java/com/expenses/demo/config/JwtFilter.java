package com.expenses.demo.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.expenses.demo.util.JwtUtility;
import com.expenses.demo.util.SecureUserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private SecureUserService secureUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get Authorization header
        final String authHeader = request.getHeader("Authorization"); // Fixed typo

        String username = null;
        String token = null;

        // Check if the header contains a valid Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Extract token
            username = jwtUtility.extractUsername(token); // Extract username from token
        }

        // Validate the token and authenticate the user
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details from the database
            UserDetails userDetails = secureUserService.loadUserByUsername(username);

            // Validate the token using user details
            if (jwtUtility.validateToken(token, userDetails)) {
                // Create authentication token and set it in the SecurityContext
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
