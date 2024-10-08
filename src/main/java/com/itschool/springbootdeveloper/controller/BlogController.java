package com.itschool.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itschool.springbootdeveloper.domain.Article;
import com.itschool.springbootdeveloper.dto.AddArticleRequest;
import com.itschool.springbootdeveloper.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/noapi/articles")
public class BlogController {
    private  final BlogService blogService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("")
    public ModelAndView addArticle(HttpServletRequest request) throws IOException{
        // request에서 JSON 데이터를 읽고, AddArticleRequest 객체로 매핑
        AddArticleRequest addArticleRequest = objectMapper.readValue(request.getInputStream(), AddArticleRequest.class);
        // 서비스 호출을 통해 Aritcle 생성
        Article savedArticle = blogService.create(addArticleRequest);

        // JSON 데이터를 담아서 반환
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("article.html", savedArticle);
        mav.setStatus((HttpStatus.CREATED));
        System.out.println(addArticleRequest);
        return mav;
    }

    // InputStream을 String으로 변환하는 메소드
    public static String convertStreamToString(InputStream inputStream) throws IOException{
        // InputStream을 BufferedReader로 감싸고 UTF-8로 인코딩 설정
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))){
            // Collectors.joining()을 통해 전체 문자열을 읽음
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
