package com.zhaojm.study.bplus.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhaojm
 * @date 2020-04-17 10:03
 */
@Data
@TableName(value = "self_user")
public class UserInfo {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String username;

    private Integer age;
}
