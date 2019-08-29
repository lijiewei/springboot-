package com.ljw.springboot.service.impl;

import com.ljw.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: Administrator
 * @CreateDate: 2019/8/19 22:22
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("insert into foo (bar) values ('aa')");
    }

    @Override
    @Transactional(rollbackFor = UnexpectedRollbackException.class)
    public void insertThenRollback() {
        jdbcTemplate.execute("insert into foo (bar) values ('bb')");
        throw new UnexpectedRollbackException("抛出错误");
    }

    @Override
    public void invokeInsertThenRollback() throws UnexpectedRollbackException{
        insertThenRollback();
    }
}
