package song.jwt1.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

public class JwtUtils {

    public static final SecretKey KEY = Jwts.SIG.HS256.key().build();

    public static String createJwt(Long id) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryTime = now.plus(Duration.ofMillis(3600000));

        Timestamp timestamp = Timestamp.valueOf(expiryTime);

        return Jwts.builder()
                .issuer("TestIss")
                .subject(String.valueOf(id))
                .expiration(timestamp)
                .issuedAt(Timestamp.valueOf(now))
                .signWith(KEY)
                .compact();
    }
}
