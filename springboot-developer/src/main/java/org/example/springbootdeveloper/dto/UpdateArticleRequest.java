package org.example.springbootdeveloper.dto;
// add와 비슷한 개념이지만 id를 통해 엔티티에 접근하고 UpdateArticleRequest에 요청 본문을 담아 수정
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateArticleRequest {
    private String title;
    private String content;
}