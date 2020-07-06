package com.wjian.study.schedule.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@RestController
@RequestMapping("/task")
public class DynamicTask {
    private static String DEFAULT_CRON = "0/5 * * * * ?";
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> future1;
    private ScheduledFuture<?> future2;
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }
    @RequestMapping("/startCron")
    public String startCron() {
        future1 = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger(DEFAULT_CRON));
        future2 = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/7 * * * * ?"));

        System.out.println("start cron");
        return "startCron";
    }
    @RequestMapping("/stopCron")
    public String stopCron()  {
        if (future1 != null) {
            future1.cancel(true);
        }
        System.out.println("stop cron");
        return "stopCron";
    }
    @RequestMapping("/changeCron")
    public String changeCron10() {
        stopCron();// 先停止，在开启.
        future1 = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/7 * * * * *"));
        System.out.println("changeCron10");
        return "changeCron10";
    }
    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("run ，" + new Date()+Thread.currentThread().getName());
        }
    }
}