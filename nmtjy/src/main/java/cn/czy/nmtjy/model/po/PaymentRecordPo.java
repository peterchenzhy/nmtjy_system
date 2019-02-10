package cn.czy.nmtjy.model.po;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentRecordPo extends BasePo {
    private Long id;

    private Long courseRegistrationId;

    private Byte times;

    private BigDecimal perTimes;

    private Byte discount;

    private BigDecimal materialPay;

    private BigDecimal otherPay;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseRegistrationId() {
        return courseRegistrationId;
    }

    public void setCourseRegistrationId(Long courseRegistrationId) {
        this.courseRegistrationId = courseRegistrationId;
    }

    public Byte getTimes() {
        return times;
    }

    public void setTimes(Byte times) {
        this.times = times;
    }

    public BigDecimal getPerTimes() {
        return perTimes;
    }

    public void setPerTimes(BigDecimal perTimes) {
        this.perTimes = perTimes;
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {
        this.discount = discount;
    }

    public BigDecimal getMaterialPay() {
        return materialPay;
    }

    public void setMaterialPay(BigDecimal materialPay) {
        this.materialPay = materialPay;
    }

    public BigDecimal getOtherPay() {
        return otherPay;
    }

    public void setOtherPay(BigDecimal otherPay) {
        this.otherPay = otherPay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}