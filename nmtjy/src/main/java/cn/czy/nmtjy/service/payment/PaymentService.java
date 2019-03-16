package cn.czy.nmtjy.service.payment;

import cn.czy.nmtjy.commons.enums.StatusEnum;
import cn.czy.nmtjy.mapper.PaymentRecordMapper;
import cn.czy.nmtjy.model.po.CoursePo;
import cn.czy.nmtjy.model.po.PaymentRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author: PeterChen
 * @Date: 2019/2/26 20:03
 * @Version 1.0
 */
@Service
public class PaymentService {
    @Autowired
    private PaymentRecordMapper paymentRecordMapper;

    public Boolean payRegistration(Integer operateUserId,Long courseRegistrationId, Integer times, Integer discount, BigDecimal otherPay, String remark) {



        return false;
    }

    public Boolean payRegistrationByCourse(Integer operateUserId, CoursePo course ,Long courseRegistrationId,Integer times, Integer discount, BigDecimal otherPay, String remark){
        PaymentRecordPo po = new PaymentRecordPo();
        po.setCourseRegistrationId(courseRegistrationId);
        po.setTimes(times);
        po.setPerTimes(po.getPerTimes());
        po.setDiscount(discount);
        po.setMaterialPay(po.getMaterialPay());
        po.setOtherPay(otherPay);
        po.setRemark(remark);
        po.setStatus(StatusEnum.有效.getCode());
        po.setCreator(operateUserId);
        po.setOperator(operateUserId);
return false ;
//        return paymentRecordMapper.insert(po)>0;
    }
}
