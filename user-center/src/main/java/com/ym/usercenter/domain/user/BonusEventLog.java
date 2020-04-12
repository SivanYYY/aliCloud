package com.ym.usercenter.domain.user;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author sivan
 * 功能描述:积分表管理
 */
@SuppressWarnings("serial")
@ApiModel(value="bonusEventLog", description="积分表")
public class BonusEventLog  implements java.io.Serializable {

	private Long id ; //Id
	private Long userId ; //user.id
	private Long value ; //积分操作值
	private String event ; //发生的事件
	private Date createTime ; //创建时间
	private String description ; //描述


	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "user.id")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ApiModelProperty(value = "积分操作值")
	public Long getValue() {
		return value;
	}
	
	public void setValue(Long value) {
		this.value = value;
	}

	@ApiModelProperty(value = "发生的事件")
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}

	@ApiModelProperty(value = "创建时间")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ApiModelProperty(value = "描述")
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}