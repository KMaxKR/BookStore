package ks.msx.BookStore.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import ks.msx.BookStore.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtUtil {
    @Value("secret.key")
    private final String key;


    public String generateToken(User user){
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            token = JWT.create()
                    .withIssuer(key)
                    .withSubject(user.getUsername())
                    .withClaim(user.getUsername(), user.getId())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 50000L))
                    .withJWTId(UUID.randomUUID().toString())
                    .withNotBefore(new Date(System.currentTimeMillis() + 5000L))
                    .sign(algorithm);
        }catch (JWTCreationException e){
            e.getStackTrace();
        }
        return token;
    }

    public void verifyToken(String token){
        // TODO: 24.10.2023 token verify function.... 
    }

}
