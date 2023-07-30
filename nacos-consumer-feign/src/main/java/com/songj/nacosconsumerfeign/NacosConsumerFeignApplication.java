package com.songj.nacosconsumerfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@RestController
public class NacosConsumerFeignApplication {

    public static void main(String[] args) {
        // Spring Webflux方式, 需要用WebClient.Builder
//        SpringApplication application = new SpringApplication(NacosConsumerFeignApplication.class);
//        application.setWebApplicationType(WebApplicationType.REACTIVE);
//        application.run(args);

        // WebMVC方式
        SpringApplication.run(NacosConsumerFeignApplication.class, args);
    }

    @Resource
    private RemoteClient remoteClient;

    // 通过feign方式调用
    @GetMapping("/feign")
    public String test1() {
        return remoteClient.ping();
    }

    // 通过rest方式调用
    @GetMapping("rest")
    public String test2() {
        String url = "http://nacos-provider/ping";
        return restTemplate.getForObject(url, String.class);
    }

    // 通过webflux WebClient.Builder
    @GetMapping("/webClientBuilder")
    public Mono<String> test3() {
        return webClientBuilder.build().get().uri("http://nacos-provider/ping").retrieve().bodyToMono(String.class);
    }

    @Bean
    @LoadBalanced //开启负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Resource
    private RestTemplate restTemplate;


    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Resource
    private WebClient.Builder webClientBuilder;

}
