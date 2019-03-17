package cn.czy.nmtjy.model.vo;

import cn.czy.nmtjy.model.po.CoursePo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourseVo extends CoursePo {

    private String schoolTypeDescription ;

    private String typeDescription;

    private String seasonDescription;

    private String progressDescription;

    private String  areaDescription ;

    private String startDateDescription ;

    private String endDateDescription ;

}