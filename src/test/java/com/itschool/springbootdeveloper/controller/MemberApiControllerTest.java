package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc // Mock MVC 생성 및 자동 구성
// Mockito : 테스트에 사용할 가짜 객체인 Mock 객체를 만들고, 관리하고, 검증할 수 있는 프레임워크
class MemberApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        System.out.println("member 테이블 전부 delete");
        memberRepository.deleteAll(); // 테스트 환경 세팅이 운영이랑 나뉘지 않아 (data.sql 실행됨) 테스트 전에 기존 데이터 전부 삭제
    }

    @AfterEach
    public void clearUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers : 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        // given : 테스트 실행 준비
        final String url = "/api/member";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when : 테스트 진행
        // perform() 메소드는 요청을 전송하는 역할(postman의 request 요청)
        // perform() 메소드의 반환 타입은 ResultActions의 객체
        final ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON));
        // accept() 메소드는 요청을 보낼 때 무슨 타입으로 응답을 받을지 결장히는 메소드
        // XML이 표준이지만 JSON으로 줄 것을 요청

        // then : 테스트 결과 검증
        // JsonPath("$[0]".$(필드명)")은 JSON 응답값을 가져오는 메소드 : 0번째 배열에 들어있는 객체의 필드를 가져옴
        result.andExpect(MockMvcResultMatchers.status().isOk()) // 응답이 OK(200) 코드인지 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(savedMember.getName()));
    }
}