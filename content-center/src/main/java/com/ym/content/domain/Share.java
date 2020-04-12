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
 * 功能描述:分享表管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
@ApiModel(value="share", description="分享表")
public class Share  implements java.io.Serializable {

	private Long id ; //id
	private Long userId ; //发布人id
	private String title ; //标题
	private Date createTime ; //创建时间
	private Date updateTime ; //修改时间
	private Long isOriginal ; //是否原创 0:否 1:是
	private String author ; //作者
	private String cover ; //封面
	private String summary ; //概要信息
	private Long price ; //价格（需要的积分）
	private String downloadUrl ; //下载地址
	private Long buyCount ; //下载数 
	private Long showFlag ; //是否显示 0:否 1:是
	private String auditStatus ; //审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过
	private String reason ; //审核不通过原因


}