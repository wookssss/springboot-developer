package com.itschool.springbootdeveloper.service;

import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.dto.UpdateArticleRequest;
import com.itschool.springbootdeveloper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article create(AddArticleRequest request){
        // save() 메소드는 JpaRepository의 부모인 CRUDRepository에 선언이 되어있음.
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+ id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
