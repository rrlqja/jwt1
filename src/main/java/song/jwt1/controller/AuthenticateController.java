package song.jwt1.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import song.jwt1.dto.LoginDto;
import song.jwt1.security.principal.UserPrincipal;
import song.jwt1.security.principal.service.UserDetailsServiceImpl;
import song.jwt1.util.JwtUtils;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthenticateController {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public String getAuthenticate(@ModelAttribute LoginDto loginDto,
                                  HttpServletResponse response) {
        Authentication authenticate = authenticate(loginDto);
        UserPrincipal userPrincipal = (UserPrincipal) authenticate.getPrincipal();

        String jwt = JwtUtils.createJwt(userPrincipal.getUsername());

        Cookie jwtCookie = new Cookie("Jwt", jwt);
        jwtCookie.setHttpOnly(true);
//        jwtCookie.setSecure();
        response.addCookie(jwtCookie);

        return "redirect:/";
    }

    private Authentication authenticate(LoginDto loginDto) {
        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDto.getUsername(),
                loginDto.getPassword()));
    }
}
