package com.ym.usercenter.config.base.service;

import com.ym.usercenter.commons.page.Pager;
import com.ym.usercenter.commons.page.SqlUtil;
import com.ym.usercenter.config.base.base.BaseObject;

public class BaseServiceImpl extends BaseObject {
	
	public void setPageParam(Pager<?> pager) {
		int totalCount = pager.getTotalCount();
		if (pager.getLimitStart() >= 0) {
            int numPerPage = SqlUtil.checkPageSize(pager.getNumPerPage());
            int currentPage = SqlUtil.checkPageCurrent(totalCount, numPerPage, pager.getPageNum());
            pager.setLimitStart(SqlUtil.countOffset(currentPage, numPerPage));
            pager.setPageSize(numPerPage);
            pager.setCurrentPage(currentPage);
            pager.setTotalCount(totalCount);
            pager.setTotalPage(totalCount % numPerPage == 0 ? totalCount / numPerPage : totalCount / numPerPage + 1);
	    }
	}
}
