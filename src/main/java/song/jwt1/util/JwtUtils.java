package song.jwt1.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

public class JwtUtils {

    public static final SecretKey KEY = Jwts.SIG.HS256.key().build();

    public static String createJwt(String username) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = now.plus(Duration.ofMillis(3600000));

        Timestamp timestamp = Timestamp.valueOf(expiryTime);

        return Jwts.builder()
                .issuer("TestIss")
                .subject(username)
                .expiration(timestamp)
                .issuedAt(Timestamp.valueOf(now))
                .signWith(KEY)
                .compact();
    }

    public static String validateJwt(String jwt) throws JwtException {
        JwtParser parser = Jwts.parser().verifyWith(KEY).build();

        Jws<Claims> jws = parser.parseSignedClaims(jwt);

        Date expiration = jws.getPayload().getExpiration();
        if (expiration.before(new Date())) {
            throw new JwtException("시간 만료");
        }

        return jws.getPayload().getSubject();
    }
}
