package com.ym.usercenter.domain.user;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author sivan
 * 功能描述:用户表管理
 */
@SuppressWarnings("serial")
@ApiModel(value="user", description="用户表")
public class User  implements java.io.Serializable {

	private Long id ; //Id
	private String wxId ; //微信id
	private String wxNickname ; //微信昵称
	private String roles ; //角色
	private String avatarUrl ; //头像地址
	private Date createTime ; //创建时间
	private Date updateTime ; //修改时间
	private Long bonus ; //积分


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "微信id")
	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	@ApiModelProperty(value = "微信昵称")
	public String getWxNickname() {
		return wxNickname;
	}

	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	@ApiModelProperty(value = "角色")
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	@ApiModelProperty(value = "头像地址")
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@ApiModelProperty(value = "创建时间")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ApiModelProperty(value = "修改时间")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@ApiModelProperty(value = "积分")
	public Long getBonus() {
		return bonus;
	}

	public void setBonus(Long bonus) {
		this.bonus = bonus;
	}
}