package cn.czy.nmtjy.commons;

/**
 * @Author: PeterChen
 * @Date: 2019/2/10 21:26
 * @Version 1.0
 */
public interface Consts {

    //服务名
    String SERVER = "nmtjy-server";

    //JWT key
    String JWT_KEY = "myJwtSecret";
    String HTTP_HEADER_TOKEN = "nmtjy-token";
    String HTTP_HEADER_BEARER = "Bearer ";
    String LONGIN_USER = "x-login-userid";

    String DATE_FORMAT = "yyyy-MM-dd";


    String ZERO_STRING = "0";


    String ECACHE_COURSE_CONFIG = "nmyjy.config.cache:10h";

    String[] CONFIG_NAME_ARRAYS = {Consts.SCHOOL_TYPE, Consts.COURSE_AREA, Consts.COURSE_PROGRESS, Consts.COURSE_SEASON, Consts.COURSE_TYPE};


    //config
    String SCHOOL_TYPE = "SCHOOL_TYPE";
    String COURSE_AREA = "COURSE_AREA";
    //进度配置
    String COURSE_PROGRESS = "COURSE_PROGRESS";
    //季节班次
    String COURSE_SEASON = "COURSE_SEASON";
    String COURSE_TYPE = "COURSE_TYPE";


}
