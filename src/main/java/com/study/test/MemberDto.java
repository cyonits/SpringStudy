package com.study.test;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String name;
    private Integer grade;
    private Double score;
    private Boolean pass;
    private LocalDate birthday;

    public boolean isEmptyDto(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (!isJavaClass(field.get(object))) {
                    if (!isEmptyDto(field.get(object))) {
                        return false;
                    }
                } else if (field.get(object) != null) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
        return true;
    }

        public boolean isJavaClass(Object object) {
            return object == null ||
//                    object.getClass().isPrimitive() ||
                    object.getClass().getName().startsWith("java.lang");
        }
}
