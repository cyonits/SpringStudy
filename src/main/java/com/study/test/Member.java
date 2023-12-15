package com.study.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer grade;
    private Double score;
    private Boolean pass;
    private LocalDate birthday;

    public void insert(MemberDto memberDto) {
        this.update(memberDto);
    }

    public void update(MemberDto memberDto) {
        this.name = memberDto.getName();
        this.grade = memberDto.getGrade();
        this.score = memberDto.getScore();
        this.pass = memberDto.getPass();
        this.birthday = memberDto.getBirthday();
    }
}
