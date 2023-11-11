package ks.msx.BookStore.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtUtil {


    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, Authentication authentication){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_KEY)
                .compact();
    }

    private Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }


    public String generateToken(Authentication authentication) {
        return generateToken(new HashMap<>(), authentication);
    }


    public boolean isTokenValid(String token) {
        final String username = extractUsername(token);
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_KEY).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Jwt was expired or incorrect");
        }
    }
}
