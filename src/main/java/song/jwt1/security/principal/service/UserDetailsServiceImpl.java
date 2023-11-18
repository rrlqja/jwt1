package song.jwt1.security.principal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import song.jwt1.repository.UserJpaRepository;
import song.jwt1.security.principal.UserPrincipal;
import song.jwt1.user.User;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserJpaRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("읎어"));

        return new UserPrincipal(findUser.getId(), findUser.getUsername(), findUser.getPassword());
    }
}
