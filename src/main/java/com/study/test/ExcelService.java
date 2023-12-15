package com.study.test;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class ExcelService {
    public <T> Sheet init(List<T> list){
        Workbook workbook = new XSSFWorkbook();
        String className = list.get(0).getClass().getName();
        String entityName = className.substring(className.lastIndexOf(".")+1);
        return workbook.createSheet(entityName + " List");
    }

    public <T> void makeHeader(Sheet sheet, List<T> list){
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        Row headerRow = sheet.createRow(0);
        int colNo = 0;

        for (Field field : fields) {
            headerRow.createCell(colNo++).setCellValue(field.getName());
        }
    }

    public <T> void makeBody(Sheet sheet, List<T> list) throws IllegalAccessException {
        int rowNo = 1;

        for (Object object : list) {
            int colNo = 0;

            Field[] fields = object.getClass().getDeclaredFields();
            Row bodyRow = sheet.createRow(rowNo++);

            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(object);
                bodyRow.createCell(colNo++).setCellValue(value != null ? value.toString() : "");
            }
        }
    }
}
