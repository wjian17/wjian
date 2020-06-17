package com.wjian.study.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjian
 * @date 2020/6/14 0014 13:02
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();


        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
//        return  builder.routes()
//                .route(r -> r.host("**.yuqiyu.com")
//                        .and().path("/api-boot-datasource-switch.html")
//                        .and().method("GET")
//                        .uri("http://blog.yuqiyu.com")
//                        .order(1)
//                        .id("blog")

    }
}
