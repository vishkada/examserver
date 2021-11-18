package com.exam.examserver.config;

import com.exam.examserver.service.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String userName=null;
        String jwtToken=null;

        if(authHeader!=null && authHeader.startsWith("Bearer")){
            jwtToken = authHeader.substring(7);
            try {
                userName = jwtUtil.extractUsername(jwtToken);
            } catch(ExpiredJwtException exp){
                System.out.println("Expired token exception !!!");
                exp.printStackTrace();
            } catch(Exception e){
                System.out.println("Something wrong with Jwt token !!!");
                e.printStackTrace();
            }

        } else{
            System.out.println("Invalid token ! Not starts with bearer !!!");
        }

        if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwtToken, userDetails)){
                UsernamePasswordAuthenticationToken userNamePwdAuthtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                userNamePwdAuthtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userNamePwdAuthtoken);
            }
        } else{
            System.out.println("Token not valid");
        }
        filterChain.doFilter(request,response);
    }
}
