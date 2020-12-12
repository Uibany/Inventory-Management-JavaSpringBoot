package sg.edu.iss.team8ca.repo;

import sg.edu.iss.team8ca.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserName(String userName);

}