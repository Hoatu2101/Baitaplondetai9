/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.filters;

/**
 *
 * @author Admin
 */
import com.tth.utils.JwtUtils;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author huu-thanhduong
 */
@Component
public class JwtFilter implements Filter {

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest
                = (HttpServletRequest) request;

        String uri = httpRequest.getRequestURI();

        if (uri.startsWith(
                httpRequest.getContextPath() + "/api/secure")) {

            String header
                    = httpRequest.getHeader("Authorization");

            if (header == null
                    || !header.startsWith("Bearer ")) {

                ((HttpServletResponse) response)
                        .sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                "Missing token");

                return;
            }

            String token = header.substring(7);

            try {

                String username
                        = JwtUtils.validateTokenAndGetUsername(token);

                if (username != null) {

                    UsernamePasswordAuthenticationToken auth
                            = new UsernamePasswordAuthenticationToken(
                                    username,
                                    null,
                                    new ArrayList<>());

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(auth);

                } else {

                    ((HttpServletResponse) response)
                            .sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    "Invalid token");

                    return;
                }

            } catch (Exception e) {

                ((HttpServletResponse) response)
                        .sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                "Token expired");

                return;
            }
        }

        chain.doFilter(request, response);
    }

}
