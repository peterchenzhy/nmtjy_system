package cn.czy.nmtjy.controller;

import cn.czy.nmtjy.commons.Consts;
import cn.czy.nmtjy.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: PeterChen
 * @Date: 2019/2/17 21:13
 * @Version 1.0
 */
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/paymentByCourse/{courseId}", method = RequestMethod.POST)
    public boolean pay(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId,
                       @PathVariable("courseId") Long courseId,
                       @RequestParam("studentId") Long studentId,
                       @RequestParam("times") Integer times) {
        return true;
    }


    /**
     * 根据报名信息 付费
     *
     * @param loginUserId
     * @param courseRegistrationId
     * @author PeterChen
     * @modifier PeterChen
     * @version v1
     * @since 2019/5/6 22:22
     */
    @RequestMapping(value = "/paymentByCourseRegistion/{id}", method = RequestMethod.POST)
    public boolean pay(@RequestHeader(Consts.LONGIN_USER) Integer loginUserId,
                       @PathVariable("id") Long courseRegistrationId) {
        return true;
    }
}
