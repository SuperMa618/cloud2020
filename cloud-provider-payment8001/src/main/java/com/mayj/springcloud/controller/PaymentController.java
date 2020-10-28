package com.mayj.springcloud.controller;

import com.mayj.springcloud.entities.CommonResult;
import com.mayj.springcloud.entities.Payment;
import com.mayj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author Mayj
 * @Date 2020/10/9 20:27
 **/
@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    //如果服务使用consul或者zk使用这个
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("discover")
    public Object discover() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***** element:" + element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVER");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @PostMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****insert result:{}",result);
        if (result > 0) {
            return new CommonResult<>(200, "插入成功,serverPort:"+serverPort , result);
        } else {
            return new CommonResult<>(301, "插入失败",null);
        }
    }

    @GetMapping("getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****insert result:{}",payment.toString());
        if (payment!=null) {
            return new CommonResult(200, "查询成功,serverPort:"+serverPort , payment);
        } else {
            return new CommonResult(301, "查询失败",null);
        }
    }

    @GetMapping("lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
