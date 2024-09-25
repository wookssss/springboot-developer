package com.itschool.springbootdeveloper.service;

import com.itschool.springbootdeveloper.entity.Member;
import com.itschool.springbootdeveloper.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service 계층 : 비즈니스 로직 처리와 비즈니스 관련된 도메인 모델의 적합성 검증
@Service
public class MemberService {

    // Service 계층 <-> Persistence 계층
    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id).get();
    }

    public List<Member> searchMembersByName(String name){
        return memberRepository.searchByName(name).get();
    }

    public Member searchMember(Long id, String name){
        return memberRepository.findByIdAndName(id,name).get();
    }
}
