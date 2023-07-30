package com.songj.nacosconsumerfeign.dubbo;

import com.songj.nacosdubboservice.DubboProviderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DubboConsumerService {


    @DubboReference(interfaceClass = DubboProviderService.class, //服务接口名
            version = "2.0.0", //服务版本，与服务提供者的版本一致
            check = false,  //启动时检查提供者是否存在，true报错，false忽略
            timeout = 3000, //服务方法调用超时时间(毫秒)
            methods = @Method(name = "ping"), //精确到服务接口的某个方法
            retries = 3) //远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
    private DubboProviderService dubboProviderService2;

    @Scheduled(fixedDelay = 2000, initialDelay = 2000)
    public void test2() {
        String rpcCallsViaDubbo = dubboProviderService2.ping("rpc by dubbo, ");
        System.out.println(rpcCallsViaDubbo); // rpc by dubbo,  I am Dubbo ProviderServiceImplV2
    }

    @DubboReference(interfaceClass = DubboProviderService.class, //服务接口名
            version = "1.0.0", //服务版本，与服务提供者的版本一致
            check = false,  //启动时检查提供者是否存在，true报错，false忽略
            timeout = 3000, //服务方法调用超时时间(毫秒)
            methods = @Method(name = "ping"), //精确到服务接口的某个方法
            retries = 3) //远程服务调用重试次数，不包括第一次调用，不需要重试请设为0
    private DubboProviderService dubboProviderService1;

    @Scheduled(fixedDelay = 2000, initialDelay = 2000)
    public void test1() {
        String rpcCallsViaDubbo = dubboProviderService1.ping("rpc by dubbo, ");
        System.out.println(rpcCallsViaDubbo); // rpc by dubbo,  I am Dubbo ProviderServiceImplV1
    }
}
