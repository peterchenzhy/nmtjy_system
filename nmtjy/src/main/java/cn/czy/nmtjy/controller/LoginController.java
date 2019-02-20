package cn.czy.nmtjy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class LoginController extends BaseController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login page";
    }
}
