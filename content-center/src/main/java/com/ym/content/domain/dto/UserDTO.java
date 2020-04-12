package com.ym.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Sivan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id ; //Id
    private String wxId ; //微信id
    private String wxNickname ; //微信昵称
    private String roles ; //角色
    private String avatarUrl ; //头像地址
    private Date createTime ; //创建时间
    private Date updateTime ; //修改时间
    private Long bonus ; //积分

}
