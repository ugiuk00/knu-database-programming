package org.example.springbootdeveloper.dto;
// 서비스 계층이 요청을 받을 떄 데이터를 교환하기 위한 객체, 컨트롤러에서 요청본문을 받을 객체
import lombok.*;
import org.example.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;

    private String content;

    // Article 객체는 빌더애너테이션으로 생성자를 정의했기 때문에 다음과 같이 명시적으로 빌더패턴으로 생성자를 사용해 객체를 생성할 수 있다.
    // 블로그 글 추가시, 저장할 데이터를 엔티티로 변환하는 용도
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}