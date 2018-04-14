package com.example.authproject.filters;

import com.example.authproject.controllers.SMSController;
import com.example.authproject.mappers.models.Account;
import com.example.authproject.services.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    private static String USERNAME = "username";
    private static String AUTH_ID = "authId";

    @Autowired
    private AccountService accountService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.info("PATH :" + httpServletRequest.getPathInfo());
        logger.info("Method :" + httpServletRequest.getMethod());

        final String username = httpServletRequest.getHeader(USERNAME);
        final String authId = httpServletRequest.getHeader(AUTH_ID);

        Account account = accountService.getAccount(username, authId);
        if (account != null) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            httpServletResponse.setStatus(403);
        }
    }
}
