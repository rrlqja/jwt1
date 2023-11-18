package song.jwt1;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import song.jwt1.service.UserService;

@Component
@RequiredArgsConstructor
public class Init {
    private final InitService initService;

    @PostConstruct
    public void setInit() {
        initService.init();
    }


    @Component
    @RequiredArgsConstructor
    private static class InitService {
        private final UserService userService;

        public void init() {
            userService.saveUser("a", "a");
        }

    }
}
