package cn.czy.nmtjy.model.req;

import cn.czy.nmtjy.commons.Consts;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: PeterChen
 * @Date: 2019/2/26 19:48
 * @Version 1.0
 */
@Getter
@Setter
public class PaymentReq implements Serializable {

    private Long courseRegistrationId ;

    private Long courseId ;

    @NotNull(message = "报名次数不能为空")
    private int times ;

    private Integer discount = 100;

    private String otherPay =Consts.ZERO_STRING;

    private String remark;
}
