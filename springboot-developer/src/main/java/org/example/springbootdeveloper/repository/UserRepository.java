package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // 스프링 데이터 Jpa는 규칙에 맞는 메소드를 선언시, 자동으로 쿼리를 만들어줌. findByEmail은 규칙에 맞는 메소드이니 이에 맞는 쿼리를 자동으로 실행해줌
    // findByEmail -> FROM users
    //                WHERE email = #{email}
}
