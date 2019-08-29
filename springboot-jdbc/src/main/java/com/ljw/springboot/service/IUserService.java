package com.ljw.springboot.service;


/**
 * @Description
 * @Author lijiewei
 * @Date 2019/8/19 11:46
 * @Version v1.0
 */
public interface IUserService {

    void insertRecord();

    void insertThenRollback();

    void invokeInsertThenRollback();
}
