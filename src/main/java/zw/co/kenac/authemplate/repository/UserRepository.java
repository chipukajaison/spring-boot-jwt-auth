package zw.co.kenac.authemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import zw.co.kenac.authemplate.model.User;

/**
 * @author Jaison.Chipuka on 6/6/2024
 * @project Auth Template
 * @email chipukajaison@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
