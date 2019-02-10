package cn.czy.nmtjy.model.po;

import java.util.Date;

/**
 * 数据库共有字段
 * @Author: PeterChen
 * @Date: 2019/2/9 15:14
 * @Version 1.0
 */
public class BasePo {
    private Integer status ;
    private Integer creator ;
    private Integer operator ;
    private Date createTime;
    private Date operateTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }


}
