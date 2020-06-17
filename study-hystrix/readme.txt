@EnableCircuitBreaker  服务端开
@EnableHystrix

服务降级：
callback
程序运行异常
超时
服务熔断触发降级
eg1:
    @Override
    @HystrixCommand(fallbackMethod = "queryEpAlipayBillFlowList_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public List<EpAlipayBillFlow> queryEpAlipayBillFlowList(String flowNo) {

        try {
            Thread.sleep(Long.parseLong(flowNo));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<EpAlipayBillFlow> list = epAlipayBillFlowMapper.queryEpAlipayBillFlowList(flowNo);
        return list;
    }


    public List<EpAlipayBillFlow> queryEpAlipayBillFlowList_TimeOutHandler(String flowNo) {
        logger.info("线程池:  " + Thread.currentThread().getName() + port + "系统繁忙或者运行报错，请稍后再试,flowNo:  " + flowNo + "\t" + "o(╥﹏╥)o");
        return new ArrayList<>();
    }


eg2:
第二种方式需要配置开启熔断
@EnableHystrix

feign:
  hystrix:
    enabled: true

    不带失败casuse状态fallback
    @FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT" ,fallback = HystrixStudyFeignServerClientFallbackFactory.class)
    feign加注解
    public interface HystrixStudyFeignServerClient

    public class HystrixStudyFeignServerClientFallbackFactory implements HystrixStudyFeignServerClient

    携带失败casuse状态，用fallbackFactory
    @FeignClient(name = "study-feignserver1", fallbackFactory = HystrixStudyFeignServerClientWithCauseFallbackFactory.class)
    feign加注解
    public interface HystrixStudyFeignServerClientWithCause

    public class HystrixStudyFeignServerClientWithCauseFallbackFactory implements FallbackFactory<HystrixStudyFeignServerClientWithCause>

eg4:
    全局配置【对于某个service层】
    配合@HystrixCommand注解使用，不加fallback，使用全局
    @DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")



服务熔断：
break
保险丝，最大服务访问量

eg:
    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }

服务限流：
flowLimit
秒杀高并发操作，限流






