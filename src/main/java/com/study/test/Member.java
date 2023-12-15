package com.study.test;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
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
        this.name = memberDto.getName();
        this.grade = memberDto.getGrade();
        this.score = memberDto.getScore();
        this.pass = memberDto.getPass();
        this.birthday = memberDto.getBirthday();
    }

    public void update(MemberDto memberDto) {
        Optional.ofNullable(memberDto.getName())
                .ifPresent(this::setName);
        Optional.ofNullable(memberDto.getGrade())
                .ifPresent(this::setGrade);
        Optional.ofNullable(memberDto.getScore())
                .ifPresent(this::setScore);
        Optional.ofNullable(memberDto.getPass())
                .ifPresent(this::setPass);
        Optional.ofNullable(memberDto.getBirthday())
                .ifPresent(this::setBirthday);
    }
}
