package cn.czy.nmtjy.commons.enums;


import lombok.Getter;

/**
 *
 * @Author: PeterChen
 * @Date: 2019/2/9 14:27
 * @Version 1.0
 */
public enum StatusEnum {
    有效(1, "有效"),
    无效(0, "无效"),
    待生效(-1, "待生效");

    StatusEnum(int code, String descrition) {
        this.code = code;
        this.descrition = descrition;
    }

    @Getter
    private int code;

    private String descrition;

    public StatusEnum getEnumByCode(Integer req) {
        if (req != null) {
            for (StatusEnum e : StatusEnum.values())
                if (req == e.getCode()) {
                    return e;
                }
        }
        throw new IllegalArgumentException("code 参数非法，找不到对应的枚举,code:" + req);

    }

}
