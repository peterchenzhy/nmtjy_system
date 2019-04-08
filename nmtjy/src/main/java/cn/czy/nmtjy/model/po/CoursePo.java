package cn.czy.nmtjy.model.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class CoursePo extends BasePo {

    private Long id;

    /**
     * 课程编码
     */
    private String code;
    /**
     * 学校类型
     */
    private Integer schoolType;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程类型
     */
    private Integer type;
    /**
     * 课程进度
     */
    private Integer progress;
    /**
     * 课程班次 春季班
     */
    private Integer season;
    /**
     * 每节课时间
     */
    private Integer perTime;
    /**
     *  目标人群
     */
    private String targetGroup;
    /**
     * 设计课次
     */
    private Integer times;
    /**
     * 每节课费用
     */
    private BigDecimal perPrice;
    /**
     * 材料费
     */
    private BigDecimal materialPay;
    /**
     * 其他费用
     */
    private BigDecimal otherPay;
    /**
     * 开始日期
     */
    private Date startDate;
    /**
     * 结束日期
     */
    private Date endDate;
    /**
     * 课程描述
     */
    private String description;
    /**
     * 年度
     */
    private Integer year;
    /**
     * 校区
     */
    private Integer area;
    /**
     * 备注
     */
    private String remark;
    /**
     * 上课时间
     */
    private String courseTime;


}