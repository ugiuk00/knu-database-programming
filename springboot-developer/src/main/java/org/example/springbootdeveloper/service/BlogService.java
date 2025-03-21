package org.example.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.AddArticleRequest;
import org.example.springbootdeveloper.dto.UpdateArticleRequest;
import org.example.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final or @NonNull 로 표기된 필드를 매개변수로 가지는 생성자를 만듦 -> 해당 필드는 꼭 초기화 되어야한다는 의미
@Service // 서비스를 빈으로 등록
public class BlogService {

    // 서비스는 레포지토리에 의존함. 이 레포지토리로 db와 상호작용함
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {

         return blogRepository.save(request.toEntity()); // db에 저장한후 저장한 엔티티를 반환
    }

    public List<Article> findAll() { //JPA 지원 메소드: findAll()

        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id)); //람다 표현식, id에 해당하는 인스턴스 찾지 못하면 에러 발생 문구 출력
    }

    public void delete(long id) {

        blogRepository.deleteById(id);
    }

    @Transactional // 해당 메소드의 트랜잭션 무결성 보장
    public Article update(long id, UpdateArticleRequest request) { //클라이언트에게 요청받은 본문을 저장할 객체가 바로 UpdateArticleRequest dto 객체이다.
        Article article = blogRepository.findById(id) //db에서 찾은 entity
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent()); // article: db에서 조회한 수정 전 객체(엔티티), request: 클라이언트에게 받은 수정할 내용 객체

        return article;
    }
}