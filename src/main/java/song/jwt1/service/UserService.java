package song.jwt1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import song.jwt1.repository.UserJpaRepository;
import song.jwt1.user.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userRepository;

    @Transactional
    public Long saveUser(String username, String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        User saveUser = userRepository.save(user);

        return saveUser.getId();
    }
}
