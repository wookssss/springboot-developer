package com.itschool.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.StringTokenizer;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Builder
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
