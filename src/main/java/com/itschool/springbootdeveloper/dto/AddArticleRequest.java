package com.itschool.springbootdeveloper.dto;

// DTO : Data Transfer Object
// 계층끼리 데이터를 교환하기 위해 사용하는 객체
// 단순하게 데이터를 전달하기 위해 사용하는 전달자 역할

import com.itschool.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

    @Override
    public String toString() {
        return "[title : " + this.title +", name content : "+this.content+"]\n";
    }
}