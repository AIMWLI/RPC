package com.songj.nacosconsumerfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author songj
 * 接口降级两种方式 fallback方式或FallbackFactory方式
 */
//@FeignClient(name = "nacos-provider", fallback = RemoteHystrix.class)
@FeignClient(name = "nacos-provider", fallbackFactory = RemoteHystrixFallbackFactory.class)
public interface RemoteClient {

    @GetMapping("/ping")
    String ping();
}
