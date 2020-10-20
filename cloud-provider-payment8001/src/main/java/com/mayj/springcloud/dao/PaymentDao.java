package com.mayj.springcloud.dao;

import com.mayj.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @InterfaceName PaymentDao
 * @Description
 * @Author Mayj
 * @Date 2020/10/9 19:57
 **/
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
