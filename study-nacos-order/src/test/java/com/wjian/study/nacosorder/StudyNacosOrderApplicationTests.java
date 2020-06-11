package com.wjian.study.nacosorder;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class StudyNacosOrderApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        log.info(dataSource.toString());
    }

}
