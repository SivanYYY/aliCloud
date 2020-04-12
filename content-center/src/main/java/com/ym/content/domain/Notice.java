package com.ym.content.domain;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sivan
 * 功能描述:提示表管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressWarnings("serial")
@ApiModel(value="notice", description="提示表")
public class Notice  implements java.io.Serializable {

	private Long id ; //id
	private String content ; //内容
	private Long showFlag ; //是否显示 0:否 1:是
	private Date createTime ; //创建时间


}