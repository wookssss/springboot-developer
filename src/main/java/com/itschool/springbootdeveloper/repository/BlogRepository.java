package com.itschool.springbootdeveloper.repository;

import com.itschool.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
}
