package com.study.test;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ExcelService excelService;

    public void createMember(MemberDto memberDto) {
        Member member = new Member();
        member.insert(memberDto);
        memberRepository.save(member);
    }

    @Transactional
    public void patchMember(MemberDto memberDto) {
        Member member = memberRepository.findById(memberDto.getId()).orElseThrow(RuntimeException::new);
        member.update(memberDto);
    }

//    public byte[] createMemberExcel() throws IOException {
//        List<Member> memberList = memberRepository.findAll();
//
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Member List");
//
//        int rowNo = 0;
//        int colNo = 0;
//        Row headerRow = sheet.createRow(rowNo++);
//        headerRow.createCell(colNo++).setCellValue("ID");
//        headerRow.createCell(colNo++).setCellValue("Name");
//        headerRow.createCell(colNo++).setCellValue("Grade");
//        headerRow.createCell(colNo++).setCellValue("Score");
//        headerRow.createCell(colNo).setCellValue("Pass");
//
//        for (Member member : memberList) {
//            Row bodyRow = sheet.createRow(rowNo++);
//            colNo = 0;
//
//            bodyRow.createCell(colNo++).setCellValue(member.getId());
//            bodyRow.createCell(colNo++).setCellValue(member.getName());
//            bodyRow.createCell(colNo++).setCellValue(member.getGrade());
//            bodyRow.createCell(colNo++).setCellValue(member.getScore());
//            bodyRow.createCell(colNo).setCellValue(member.getPass());
//        }
//
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        sheet.getWorkbook().write(byteArrayOutputStream);
//        return byteArrayOutputStream.toByteArray();
//    }

    public byte[] createMemberExcel() throws IOException, IllegalAccessException {
        List<Member> memberList = memberRepository.findAll();

        Sheet sheet = excelService.init(memberList);
        excelService.makeHeader(sheet, memberList);
        excelService.makeBody(sheet, memberList);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        sheet.getWorkbook().write(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
