package com.ym.content.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sivan
 * 功能描述:用户-分享中间表管理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressWarnings("serial")
@ApiModel(value="midUserShare", description="用户-分享中间表")
public class MidUserShare  implements java.io.Serializable {

	private Long id ; //
	private Long shareId ; //share.id
	private Long userId ; //user.id


}