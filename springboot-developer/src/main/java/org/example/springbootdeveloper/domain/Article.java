// 도메인은 여러 엔터티(테이블)를 모아논 폴더

package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;


@Entity // db의 테이블과 매핑하는 애너테이션 (의존성주입과는 관련이 없다.)
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC) // 접근 제어자가 PUBLIC인, 파라미터가 없는 기본 생성자(1)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // 생성자 위에 붙여주며, 객체 생성시 직관적으로 생성할 수 있다. 명확한 매개변수를 받는 생성자 정의(2)
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 업데이트 = 재생성자 느낌으로 이해
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @CreatedDate //엔터티 생성될때, 생성시간을 해당 컬럼에 자동 저장
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedBy //엔터티수정될때, 마지막 수정 시간을 해당 컬럼에 자동 저장
    @Column(name="updated_at")
    private LocalDateTime updatedAt;


}


// 생성자는 목적에 맞게 여러 타입으로 동시에 정의될 수 있다.