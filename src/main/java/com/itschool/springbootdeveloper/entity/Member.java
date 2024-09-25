package com.itschool.springbootdeveloper.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 프록시 조회에서 접근을 위해 protected(지연 로딩)
@Getter
@Entity

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false) // DB 실제 컬럼명, name을 넣지 않으면 변수 명을 lower_snake_case로 간주
    private Long id; // DB 테이블의 "id" 컬럼과 매칭
    @Column(name = "name", nullable = false)
    private String name; // DB 테이블의 "name" 컬럼과 매칭
}
