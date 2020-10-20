package com.mayj.springcloud.service;

import com.mayj.springcloud.entities.Payment;

/**
 * @InterfaceName PaymentService
 * @Description TODO
 * @Author Mayj
 * @Date 2020/10/9 20:23
 **/
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
