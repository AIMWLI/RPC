package com.songj.nacosconsumerfeign;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * FallbackFactory
 *
 * @author songj
 */
@Component
public class RemoteHystrixFallbackFactory implements FallbackFactory<RemoteClient> {
    @Override
    public RemoteClient create(Throwable cause) {
        // 与fallback的方式类似 需要返回一个对应接口的对象
        return new RemoteClient() {
            @Override
            public String ping() {
                System.err.println("I am FallbackFactory");
                return "I am FallbackFactory";
            }
        };
    }
}
