package cn.czy.nmtjy.model.po;


public class TeacherPo extends BasePo {
    private Long id;

    private String name;

    private Integer birthday;

    private String school;

    private Integer majorCourse;

    private Integer majorGrade;

    private Boolean star;


    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getMajorCourse() {
        return majorCourse;
    }

    public void setMajorCourse(Integer majorCourse) {
        this.majorCourse = majorCourse;
    }

    public Integer getMajorGrade() {
        return majorGrade;
    }

    public void setMajorGrade(Integer majorGrade) {
        this.majorGrade = majorGrade;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}