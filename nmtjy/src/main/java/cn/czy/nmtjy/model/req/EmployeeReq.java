package cn.czy.nmtjy.model.req;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:40
 * @Version 1.0
 */
@Setter
@Getter
public class EmployeeReq implements Serializable {

    private static final long serialVersionUID = 1827846016972181888L;
    @NotNull(message = "用户姓名不能为空")
    private String name;

    private Integer birthday;
    @NotNull(message = "请输入密码")
    private String password;

    private String passwordAgain;

    private String remark;

    @NotNull(message = "请输入手机")
    private String tel;
}
