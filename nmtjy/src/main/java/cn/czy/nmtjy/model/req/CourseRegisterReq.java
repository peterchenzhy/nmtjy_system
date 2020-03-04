package cn.czy.nmtjy.model.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * CourseRegisterReq
 *
 * @author PeterChen
 * @summary CourseRegisterReq
 * @Copyright (c) 2019, Lianjia Group All Rights Reserved.
 * @Description CourseRegisterReq
 * @since 2019-06-08 10:10
 */
@Data
public class CourseRegisterReq {
    @NotNull(message = "课程id不能为空")
    private Long courseId;
    @NotNull(message = "学生id不能为空")
    private Long studentId;
    @NotNull(message = "报名次数不能为空")
    private Integer times;

}
