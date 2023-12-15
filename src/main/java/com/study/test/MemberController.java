package com.study.test;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberController {
    final private  MemberService memberService;

    @PostMapping
    public boolean MemberTest(@RequestBody MemberDto memberDto){
        return  memberDto.isEmptyDto(memberDto);
    }

    @PostMapping
    @RequestMapping(value = "/create")
    public void createMember(@RequestBody MemberDto memberDto){
        memberService.createMember(memberDto);
    }

    @PatchMapping
    @RequestMapping(value = "/patch")
    public void patchMember(@RequestBody MemberDto memberDto){
        memberService.patchMember(memberDto);
    }

    @GetMapping
    @RequestMapping(value = "/excel")
    public ResponseEntity<byte[]> getMemberExcel() throws IOException, IllegalAccessException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "member.xlsx");

        byte[] fileContent = memberService.createMemberExcel();

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileContent.length)
                .body(fileContent);
    }
}