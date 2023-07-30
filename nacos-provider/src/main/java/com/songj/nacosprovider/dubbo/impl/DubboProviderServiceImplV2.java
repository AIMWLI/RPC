package com.songj.nacosprovider.dubbo.impl;

import com.songj.nacosdubboservice.DubboProviderService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * DubboProviderService实现类2
 *
 * @author songj
 * @see com.songj.nacosdubboservice.DubboProviderService
 */
@DubboService(interfaceClass = DubboProviderService.class, version = "2.0.0")
public class DubboProviderServiceImplV2 implements DubboProviderService {
    @Override
    public String ping(String name) {
        return name + "I am Dubbo ProviderServiceImplV2";
    }
}
