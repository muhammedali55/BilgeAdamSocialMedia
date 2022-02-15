package com.bilgeadam.config.security;

import com.bilgeadam.dto.request.LoginDto;
import com.bilgeadam.dto.response.DoLoginResponseDto;
import com.bilgeadam.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Kendine gelen tüm isterkleri karşılayacak ve bu isterlerde olan bilgiere göre bir filter oluşturarak
 * kullanıcı bilgilerini spring auth a verecek.
 */
@Component
@Slf4j
public class MvcTokenFilter extends OncePerRequestFilter {

    @Autowired
    BilgeUserDetailService bilgeUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String password = request.getParameter("password");
        String username = request.getParameter("email");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(bilgeUserDetailService.loadUserByUsername("bilgeadam")
                ,null,bilgeUserDetailService.loadUserByUsername("bilgeadam").getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        /**
         *
         */
        filterChain.doFilter(request, response);
    }
}
