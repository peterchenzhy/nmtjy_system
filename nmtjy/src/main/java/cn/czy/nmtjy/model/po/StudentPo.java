package cn.czy.nmtjy.model.po;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentPo extends BasePo {
    private Long id;

    private String name;

    private Integer birthday;

    private String elementarySchool;

    private String middleSchool;

    private String highSchool;

    private String grade;

    private String remark;


}