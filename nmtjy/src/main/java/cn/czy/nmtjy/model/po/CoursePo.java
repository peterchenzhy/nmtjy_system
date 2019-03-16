package cn.czy.nmtjy.model.po;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class CoursePo extends BasePo {

    private Long id;

    private Integer code;

    private Integer schoolType;

    private String name;

    private Integer type;

    private Integer progress;

    private Integer season;

    private Integer perTime;

    private Integer grade;

    private Integer times;

    private BigDecimal perPrice;

    private BigDecimal materialPay;

    private BigDecimal otherPay;

    private Date startDate;

    private Date endDate;

    private String description;

    private Integer year ;

    private Integer area;

    private String remark;

    private String courseTime;


}