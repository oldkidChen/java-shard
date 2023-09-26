package com.shard.convert;

import lombok.Data;
import lombok.ToString;

/**
 * @author chen_jinglong
 * @description:
 * @date 2023/8/4 14:13
 */

@Data
public class UserInfo {

    // ID，名字，年龄
    private Integer userId;
    private String userName;
    private Integer userAge;

    public UserInfo(){}
    public UserInfo(Integer userId, String userName, Integer userAge) {
        this.userId = userId;
        this.userName = userName;
        this.userAge = userAge;
    }

    @Override
    public String toString(){
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                '}';
    }

}
