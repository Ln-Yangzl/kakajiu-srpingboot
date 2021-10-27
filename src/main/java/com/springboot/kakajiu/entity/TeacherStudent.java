package com.springboot.kakajiu.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author zlyang
 * date: 2021-10-27
 * discription: 关系类teacerh_student的对应类，记录老师和学生的关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TeacherStudent implements Serializable {

    private int teacherId;
    private int studentId;

}
