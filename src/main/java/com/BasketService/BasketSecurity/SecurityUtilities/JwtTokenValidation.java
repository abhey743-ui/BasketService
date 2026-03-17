package com.BasketService.BasketSecurity.SecurityUtilities;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;



@Component
public class JwtTokenValidation {
      private String Secret_Key =  "7493ndddsjmkjalsdjlajdsaJDIOWUROIWDJWKXSNMLKU";

    public SecretKey getSecret(){
        return  Keys.hmacShaKeyFor(Secret_Key.getBytes(StandardCharsets.UTF_8));
    }
    public Claims verifyToken(String token){
        return  Jwts.parserBuilder().setSigningKey(getSecret()).build().parseClaimsJws(token).getBody();
    }
}
