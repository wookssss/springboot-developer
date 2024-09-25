package com.itschool.springbootdeveloper.controller;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Presentation 계층 : 웹 클라이언트의 요청 및 응답을 처리
// RESTController 또는 Controller는 라우터 역할을 함
// 라우터는 HTTP 요청과 메소드를 연결하는 장치
// @RestController 애너테이션은 @Component를 포함
@RestController
@RequestMapping("/api/member")
public class MemberApiController {
    @Autowired
    MemberService memberService;

    // TestController를 라우터로 지정해 /test라는 GET 요청이 오면 test() 메소드를 실행
    @GetMapping("") // /api/member
    public List<Member> getAllMembers() {
        // Presentation 계층은 Service 계층과 관련있음
        List<Member> members = memberService.getAllMembers();
        return members;
    }

    @GetMapping("{id}") // /api/member/(id)
    public Member findMemberById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }

    @GetMapping("/searchByName/{name}")
    public List<Member> searchMembersByName(@PathVariable String name) {
        return memberService.searchMembersByName(name);

    }

    @GetMapping("/member/id={id}&name={name}")
    // id와 name을 둘 다 받아서 데이터를 return
    // repository findByIdAndName()
    public Member searchMember(@PathVariable Long id,@PathVariable String name){
        return memberService.searchMember(id,name);
    }
}
