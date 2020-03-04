package cn.czy.nmtjy.model.po;

import lombok.Data;

@Data
public class CourseRegistrationVo extends BasePo {
    private Long id;

    private Long courseId;
    private String courseName;
    private String courseCode ;

    private Long studentId;
    private String studentName;

    private Integer times;

    private Integer payStatus;
    private String payStatusString;

    private String remark;


}