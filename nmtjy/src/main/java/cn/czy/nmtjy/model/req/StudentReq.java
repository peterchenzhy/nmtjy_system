package cn.czy.nmtjy.model.req;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:40
 * @Version 1.0
 */
public class StudentReq implements Serializable {

    private static final long serialVersionUID = 1403712817457813991L;

    @NotNull(message = "用户姓名不能为空")
    private String name;

    private Integer birthday;

    private String elementarySchool;

    private String middleSchool;

    private String highSchool;

    private Integer grade;

    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public String getElementarySchool() {
        return elementarySchool;
    }

    public void setElementarySchool(String elementarySchool) {
        this.elementarySchool = elementarySchool;
    }

    public String getMiddleSchool() {
        return middleSchool;
    }

    public void setMiddleSchool(String middleSchool) {
        this.middleSchool = middleSchool;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
