package ghtk.spring.hw2.repository;

import ghtk.spring.hw2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Integer> {
}
