package cn.czy.nmtjy.model.po;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class PaymentRecordPo extends BasePo {
    private Long id;

    private Long courseRegistrationId;

    private Integer times;

    private BigDecimal perTimes;

    private Integer discount;

    private BigDecimal materialPay;

    private BigDecimal otherPay;

    private String remark;


}