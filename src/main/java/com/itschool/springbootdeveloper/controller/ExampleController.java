package com.itschool.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller // 컨트롤러 명시
public class ExampleController {
    @GetMapping("/thymeleaf/example")
    public String thymeleafEameple(Model model){ // 뷰로 데이터를 넘겨주는 모델 객체
        Person exampplePerson = new Person();
        exampplePerson.setId(1L);
        exampplePerson.setName("홍길동");
        exampplePerson.setAge(11);
        exampplePerson.setHobbies(List.of("운동","독서"));

        model.addAttribute("person",exampplePerson);
        model.addAttribute("today", LocalDate.now());
        return "example"; // example.html 조회
        // src/main/resources/templates 폴더에 있는 파일 조회
    }

    @Getter
    @Setter
    class Person{
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
