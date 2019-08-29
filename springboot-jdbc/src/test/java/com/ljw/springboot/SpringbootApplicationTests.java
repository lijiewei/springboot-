package com.ljw.springboot;

import com.ljw.springboot.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootApplicationTests {


    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private IUserService userService;


    @Test
    public void dataSourceTest() throws SQLException {
        log.info(dataSource.toString());
        Connection conn = dataSource.getConnection();
        log.info(conn.toString());
        conn.close();
    }

    @Test
    public void jdbcTemplateTest() {
        jdbcTemplate.queryForList("select * from foo").forEach(o -> log.info(o.toString()));
    }

    @Test
    public void rollbackTest() {
        userService.insertRecord();
        log.info("aa {}", jdbcTemplate.queryForObject("select count(*) from foo where bar = 'aa'", Long.class));
        jdbcTemplate.queryForList("select * from foo").forEach(o -> log.info(o.toString()));
        try {
            userService.insertThenRollback();
        } catch (Exception e) {
            log.info("bb {}", jdbcTemplate.queryForObject("select count(*) from foo where bar = 'bb'", Long.class));
        }
        try {
            userService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("bb {}", jdbcTemplate.queryForObject("select count(*) from foo where bar = 'bb'", Long.class));
        }
    }

}
