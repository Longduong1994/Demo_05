package demo.security.jwt;

import demo.security.user_principle.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    public  final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private final String SECRET_KEY ="longduong";

    public String generateToken(UserPrinciple userPrinciple){
        int EXPIRED = 86400000;
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+ EXPIRED))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;

        } catch (ExpiredJwtException e) {
            logger.error("Failed -> Expired Token Message {}", e.getMessage());
        }catch (UnsupportedJwtException e) {
            logger.error("Failed -> Unsupported Token Message {}", e.getMessage());
        }catch (MalformedJwtException e) {
            logger.error("Failed -> Invalid Format Token Message {}", e.getMessage());
        }catch (SignatureException e) {
            logger.error("Failed -> Invalid Signature Token Message {}", e.getMessage());
        }catch (IllegalArgumentException e) {
            logger.error("Failed -> Claims Empty Token Message {}", e.getMessage());
        }
        return false;
    }

    public  String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY)
                .parseClaimsJws(token).getBody().getSubject();
    }
}
