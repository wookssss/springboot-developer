package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.ArticleListViewResponse;
import com.itschool.springbootdeveloper.dto.ArticleViewResponse;
import com.itschool.springbootdeveloper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        model.addAttribute("articles", articles); // 블로그 글 리스트 모델에 저장
        
        return "articleList"; // articleList.html이라는 뷰 조회
    }

    @GetMapping("articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);

        model.addAttribute("article", new ArticleViewResponse(article));

        return "article"; // 뷰 리턴(article.html이 존재해야 함)
    }

    @GetMapping("/new-article")
    // id를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
    public String newArticle(@RequestParam(required = false) Long id, Model model) {

        System.out.println("--------------------------------");
        System.out.println(id);
        System.out.println("--------------------------------");

        if (id == null) { // id가 없으면 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else { // id가 없으면 수정
            Article article = blogService.findById(id);
            model.addAttribute("article", new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}






