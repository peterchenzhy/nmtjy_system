package cn.czy.nmtjy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: PeterChen
 * @Date: 2019/2/10 15:09
 * @Version 1.0
 */
@RestController
public class TestController extends BaseController {

    @RequestMapping("/test1")
    public String test1(){
        return "hello";
    }
}
