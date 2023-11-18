package song.jwt1.security.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import song.jwt1.security.principal.UserPrincipal;
import song.jwt1.util.JwtUtils;

import java.io.IOException;

@Slf4j
public class JwtSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("AuthenticationSuccess username = {}, password= {}", request.getParameter("username"), request.getParameter("password"));
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long userId = userPrincipal.getId();

        String jwt = JwtUtils.createJwt(userId);

        response.setContentType("application/json");
        response.getWriter().write(jwt);
    }
}
