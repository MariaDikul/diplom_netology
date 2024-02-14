package ru.netology.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.diplom.domain.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
}