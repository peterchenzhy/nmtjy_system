package cn.czy.nmtjy.commons.enums;


import lombok.Getter;

/**
 * 支付状态枚举
 *
 * @Author: PeterChen
 * @Date: 2019/2/17 14:27
 * @Version 1.0
 */
public enum PayStatusEnum {
    未支付(0, "未支付"),
    已支付(1, "已支付"),
    已申请退款(2, "已申请退款"),
    已退款(3, "已退款");

    PayStatusEnum(int code, String descrition) {
        this.code = code;
        this.descrition = descrition;
    }

    @Getter
    private int code;

    @Getter
    private String descrition;

    public static PayStatusEnum getEnumByCode(Integer req) {
        if (req != null) {
            for (PayStatusEnum e : PayStatusEnum.values())
                if (req == e.getCode()) {
                    return e;
                }
        }
        throw new IllegalArgumentException("code 参数非法，找不到对应的枚举,code:" + req);

    }

}
