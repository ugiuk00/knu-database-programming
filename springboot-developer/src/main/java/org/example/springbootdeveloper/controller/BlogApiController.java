// 해당 컨트롤러는 반환값이 뷰가 아님
package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.ArticleResponse;
import org.example.springbootdeveloper.dto.UpdateArticleRequest;
import org.example.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles") //PostMapping이니까 해당 url에서 사용자 요청본문을 보낼것임. //@RequestBody: 클라이언트가 보내는 json 요청본문을 AddArticleRequest 객체로 매핑
    public ResponseEntity<Article> addArticle(@RequestBody org.example.springbootdeveloper.dto.AddArticleRequest request) {
        // ResponseEntity: Http 응답을 나타내는 객체
        Article savedArticle = blogService.save(request); // db에 저장되어 반환된 엔티티를 savedArticle에 저장

        return ResponseEntity.status(HttpStatus.CREATED) // 상태코드를 201로 설정하며,
                .body(savedArticle);                     // 응답본문에 savedArticle을 포함
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
                // Article 객체를 ArticleResponse 객체에 매핑하려면 ArticleResponse객체를 새로 생성해야하기때문에 new 키워드 사용
        return ResponseEntity.ok()  // ResponseEntity: 유사 resultAction 객체 (응답코드, 응답본문, 헤더 포함) -> 검증 가능
                .body(articles); // ok(): 200응답코드 설정, body(): 응답 본문 설정
    }
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) { //@PathVariable: 요청 url에서 값 파싱
        Article article = blogService.findById(id);

        return ResponseEntity.ok() //200코드 생성
                .body(new ArticleResponse(article)); //ArticleResponse객체는 Article객체를 받아 응답객체로 만들어주는 dto 객체이다.
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build(); // 요청만 성공해서 응답만 보낼때 build()로 응답 생성(응답 본문이 없을때)
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }

}