package cn.czy.nmtjy.commons.enums;


import lombok.Getter;

/**
 * 课程congig枚举
 *
 * @Author: PeterChen
 * @Date: 2019/2/9 14:27
 * @Version 1.0
 */
public enum CourseConfigType {
        课程(1, "课程"),
        进度(2, "进度"),
        班次(3, "班次"),
        每节课时间(4, "每节课时间");

        CourseConfigType(int code, String descrition) {
            this.code = code;
            this.descrition = descrition;
        }

        @Getter
        private int code;

        private String descrition;

        public CourseConfigType getEnumByCode(Integer req)
        {
            if (req != null) {
                for (CourseConfigType e : CourseConfigType.values())
                    if (req == e.getCode()) {
                        return e;
                    }
            }
            throw  new  IllegalArgumentException("code 参数非法，找不到对应的枚举,code:"+req);

    }

}
