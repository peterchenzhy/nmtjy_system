package cn.czy.nmtjy.model.req;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:40
 * @Version 1.0
 */
@Getter
@Setter
public class CourseReq implements Serializable {

    private static final long serialVersionUID = -6315797870239067109L;

    private Long id;

    @NotNull(message = "课程名字不能为空")
    private String name;
    @NotNull(message = "课程类型不能为空")
    private Integer type;
    @NotNull(message = "课程进度类型不能为空")
    private Integer progress;
    @NotNull(message = "课程季节类型不能为空")
    private Integer season;
    @NotNull(message = "课程每节课时间不能为空")
    private String perTime;

    private Integer grade; //目标年级

    @NotNull(message = "课程次数不能为空")
    private Integer times;
    @NotNull(message = "每节课单价不能为空")
    private BigDecimal perPrice;
    @NotNull(message = "材料费")
    private BigDecimal materialPay;

    private BigDecimal otherPay; //其他费用

    @NotNull(message = "课程开始时间不能为空")
    private Integer start_date;
    @NotNull(message = "课程结束时间不能为空")
    private Integer end_date;

    private String description;//课程描述
    private String remark;//备注


}
