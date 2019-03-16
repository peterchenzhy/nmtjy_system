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
public class CourseSearchReq implements Serializable {

    private static final long serialVersionUID = -6315797870239067109L;

    private Long id;

    private String name;
    private Integer type;
    private Integer progress;
    private Integer season;
    private String perTime;

    private Integer grade; //目标年级

    private Integer start_date;
    private Integer end_date;


}
