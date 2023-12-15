package com.study.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test
    public void jpaUpdateTest(){
        Member member = new Member("kim");
        memberRepository.save(member);

        memberService.update(member.getId(),"lee");

        Member updatedMember = memberRepository.findAll().get(0);

        assertThat(updatedMember.getName()).isEqualTo("lee");
    }
}