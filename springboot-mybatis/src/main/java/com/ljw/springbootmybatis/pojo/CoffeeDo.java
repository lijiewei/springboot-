package com.ljw.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.util.Date;

/**
 * @Description:
 * @Author: Administrator
 * @CreateDate: 2019/8/29 8:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoffeeDo {
    private Long id;
    private String name;
    private Money price;
    private Date createTime;
    private Date updateTime;
}
