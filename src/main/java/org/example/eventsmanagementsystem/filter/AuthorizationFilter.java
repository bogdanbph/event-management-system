package org.example.eventsmanagementsystem.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.eventsmanagementsystem.converter.util.PasswordEncoder;
import org.example.eventsmanagementsystem.exception.MissingAuthorizationHeaderException;
import org.example.eventsmanagementsystem.exception.UserNotAllowedException;
import org.example.eventsmanagementsystem.model.dto.UserDTO;
import org.example.eventsmanagementsystem.service.UserService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.MalformedParametersException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Order(2)
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;

    public AuthorizationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || authorization.isEmpty()) {
            throw new MissingAuthorizationHeaderException("Missing Authorization header...");
        }

        String[] basicSegments = authorization.split("Basic ");
        if (basicSegments.length > 1) {
            String authEncoded = basicSegments[1];
            String credentials = new String(Base64.getDecoder().decode(authEncoded), StandardCharsets.UTF_8);

            String[] usernameAndPassword = credentials.split(":");
            UserDTO userByUsername = userService.getUserByUsername(usernameAndPassword[0]);

            if (PasswordEncoder.encodeDecodePassword(userByUsername.password(), false).equals(usernameAndPassword[1])) {
                request.setAttribute("user", userByUsername);
                filterChain.doFilter(request, response);
            } else {
                throw new UserNotAllowedException("Unable to identify user...");
            }
        } else {
            throw new MalformedParametersException("Wrong Authorization header...");
        }
    }
}
