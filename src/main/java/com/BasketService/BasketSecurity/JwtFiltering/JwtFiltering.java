package com.BasketService.BasketSecurity.JwtFiltering;
import com.BasketService.BasketSecurity.SecurityUtilities.JwtTokenValidation;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;



@AllArgsConstructor
public class JwtFiltering extends OncePerRequestFilter {

    private final JwtTokenValidation jwtTokenValidation;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                              String token = null;
                                   if(request.getCookies() !=null){
                                         for(Cookie c: request.getCookies()){

                                             if(c.getName().equals("JWT")){
                                                          token = c.getValue();
                                             }
                                         }
                                   }
                              Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                             if(token !=null && auth == null){
                                   Claims claims =  jwtTokenValidation.verifyToken(token);
                                 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(claims,null,null);
                                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                                 filterChain.doFilter(request,response);


                             }
                             filterChain.doFilter(request,response);
    }
}
