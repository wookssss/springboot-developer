package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.dto.ArticleResponse;
import com.itschool.springbootdeveloper.dto.UpdateArticleRequest;
import com.itschool.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class BlogApiController {
    private final BlogService blogService;

    @GetMapping("{id}")
    public ResponseEntity<ArticleResponse> findArticles(@PathVariable Long id){
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @GetMapping("")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        /*List<Article> articleList = blogService.findAll();
        List<ArticleResponse> articles = new ArrayList<>();

        for(Article article : articleList) {
            articles.add(new ArticleResponse(article));
        }*/
        List<ArticleResponse> articles = blogService.findAll()
                                                    .stream()
                                                    .map(ArticleResponse :: new)
                                                    .toList();
        return ResponseEntity.ok().body(articles);
    }
    @PostMapping("")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(savedArticle);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @Transactional(rollbackFor = RuntimeException.class) // 트랜젝션 메소드
    @PutMapping("{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article updateArticle = blogService.update(id,request);

        return ResponseEntity.ok().body(updateArticle);
    }
}
