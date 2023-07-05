package com.practice.studenthub.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.practice.studenthub.exception.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (EntityNotFoundException e) {
            sendResponse(response, HttpServletResponse.SC_NOT_FOUND, "Username doesn't exist.");
        } catch (JWTVerificationException e) {
            sendResponse(response, HttpServletResponse.SC_FORBIDDEN, "JWT not valid.");
        } catch (RuntimeException e) {
            sendResponse(response, HttpServletResponse.SC_BAD_REQUEST, "Bad request.");
        }
    }

    private void sendResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.getWriter().write(message);
        response.getWriter().flush();
    }
}
