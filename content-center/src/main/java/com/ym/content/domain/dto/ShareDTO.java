package com.ym.content.domain.dto;

import com.ym.content.domain.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author sivan
 * 功能描述:分享表管理 扩展
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SuppressWarnings("serial") 
public class ShareDTO extends Share implements java.io.Serializable {

    /**
     * 发布人
     */
    private String wxNickname;

}