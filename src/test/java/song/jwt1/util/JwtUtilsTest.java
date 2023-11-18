package song.jwt1.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static song.jwt1.util.JwtUtils.KEY;

class JwtUtilsTest {

    JwtUtils jwtUtils = new JwtUtils();

    @Test
    void createJwtTest() {
        String jwt = jwtUtils.createJwt(1L);

        Jws<Claims> jws = Jwts.parser().verifyWith(KEY).build().parseSignedClaims(jwt);
        String subject = jws.getPayload().getSubject();
        Date expiration = jws.getPayload().getExpiration();
        Date issuedAt = jws.getPayload().getIssuedAt();

        System.out.println(subject);
        System.out.println(expiration);
        System.out.println(issuedAt);
    }

    @Test
    void signException() {
        SecretKey MYKEY = Jwts.SIG.HS256.key().build();
        String jwt = jwtUtils.createJwt(1L);

        assertThatThrownBy(() -> Jwts.parser().verifyWith(MYKEY).build().parseSignedClaims(jwt))
                .isInstanceOf(JwtException.class);

    }

}