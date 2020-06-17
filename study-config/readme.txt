/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties

手动刷新
@RefreshScope
#刷新单个服务
curl -X POST "http://localhost:8767/actuator/refresh"

消息总线bus
management:
  endpoints:
    web:
      exposure:
        include: bus-refresh


服务端：客户端：导入pom.xml
        <!--添加消息总线支持-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-kafka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
@RefreshScope
#刷新消息总线
curl -X POST "http://localhost:8766/actuator/bus-refresh"
#定点通知
curl -X POST "http://localhost:8766/actuator/bus-refresh/${client.spring.application.name}:${client.server.port}"


http://localhost:8766/master/config-test.yml

http://localhost:8766/config/test/master
