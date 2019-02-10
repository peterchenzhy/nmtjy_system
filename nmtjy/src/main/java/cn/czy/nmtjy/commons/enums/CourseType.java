package cn.czy.nmtjy.commons.enums;


import lombok.Getter;

/**
 * 课程类型枚举
 *
 * @Author: PeterChen
 * @Date: 2019/2/9 14:27
 * @Version 1.0
 */
public enum  CourseType {
        语文(1, "语文"),
        数学(2, "数学"),
        英语(3, "英语"),
        物理(4, "物理"),
        化学(5, "化学"),
        生物(6, "生物"),
        地理(7, "地理"),
        历史(8, "历史");

        CourseType(int code, String descrition) {
            this.code = code;
            this.descrition = descrition;
        }

        @Getter
        private int code;

        private String descrition;

        public CourseType getEnumByCode(Integer req)
        {
            if (req != null) {
                for (CourseType e : CourseType.values())
                    if (req == e.getCode()) {
                        return e;
                    }
            }
            throw  new  IllegalArgumentException("code 参数非法，找不到对应的枚举,code:"+req);

    }

}
