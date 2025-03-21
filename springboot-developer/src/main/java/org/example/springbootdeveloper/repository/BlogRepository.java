package org.example.springbootdeveloper.repository;

import org.example.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
// 스프링 데이터 jpa가 제공하는 JpaRepository의 메서드를 사용할 수 있음
public interface BlogRepository extends JpaRepository<Article, Long> {
}

// JpaRepository<엔티티 타입, PK타입>