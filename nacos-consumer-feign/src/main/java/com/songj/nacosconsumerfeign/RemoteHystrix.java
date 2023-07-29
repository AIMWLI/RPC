package com.songj.nacosconsumerfeign;

import org.springframework.stereotype.Component;

/**
 * fallback
 *
 * @author songj
 */
@Component
public class RemoteHystrix implements RemoteClient {

    @Override
    public String ping() {
        return "I am fallback";
    }
}
