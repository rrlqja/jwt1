package song.jwt1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import song.jwt1.user.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

}
