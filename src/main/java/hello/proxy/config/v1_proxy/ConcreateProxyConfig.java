package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2_noInterface.OrderControllerV2;
import hello.proxy.app.v2_noInterface.OrderRepositoryV2;
import hello.proxy.app.v2_noInterface.OrderServiceV2;
import hello.proxy.config.v1_proxy.concreate_proxy.OrderControllerConcreateProxy;
import hello.proxy.config.v1_proxy.concreate_proxy.OrderRepositoryConcreateProxy;
import hello.proxy.config.v1_proxy.concreate_proxy.OrderServiceConcreateProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreateProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 controllerV2 = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreateProxy(controllerV2, logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreateProxy(orderServiceV2, logTrace);
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 repositoryV2 = new OrderRepositoryV2();
        return new OrderRepositoryConcreateProxy(repositoryV2, logTrace);
    }
}
