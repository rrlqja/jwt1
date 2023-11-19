package song.jwt1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import song.jwt1.dto.LoginDto;
import song.jwt1.security.principal.UserPrincipal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "/home";
    }

    @GetMapping("/login")
    public String getLogin(@ModelAttribute LoginDto loginDto) {
        return "/login";
    }

    @GetMapping("/mypage")
    public String getMyPage(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        return "mypage";
    }
}
