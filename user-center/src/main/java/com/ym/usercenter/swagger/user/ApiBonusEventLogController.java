package com.ym.usercenter.swagger.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ym.usercenter.commons.remsg.WebResout;
import com.ym.usercenter.config.ApiBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ym.usercenter.commons.exceptions.BusinessException;
import com.ym.usercenter.commons.page.Pager;
import com.ym.usercenter.domain.user.BonusEventLog;
import com.ym.usercenter.domain.user.dto.BonusEventLogDTO;
import com.ym.usercenter.service.user.IBonusEventLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "bonusEventLogApi", description = "积分表对外接口")
@RestController
@RequestMapping("/api/user/bonusEventLog/1.0")
public class ApiBonusEventLogController extends ApiBaseController {

	@Autowired
	private IBonusEventLogService bonusEventLogService;

	/**
	 * 分页查看
	 */
	@ApiOperation(value = "列表", notes = "查询积分表列表",response=BonusEventLog.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "没有查找到积分表") })
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "currentPage", value = "当前页", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "numPerPage", value = "每页显示条数，默认20条", defaultValue = "20", required = true, dataType = "int", paramType = "query"), 
		@ApiImplicitParam(name = "orderField", value = "排序字段", dataType = "string", paramType = "query"), 
		@ApiImplicitParam(name = "orderDirection", value = "排序方式 ASC|DESC", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "id", value = "Id", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "userId", value = "user.id", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "value", value = "积分操作值", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "event", value = "发生的事件", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
		@ApiImplicitParam(name = "description", value = "描述", dataType = "string", paramType = "query"),
	})
	@RequestMapping(value = LIST, method = RequestMethod.GET)
	public WebResout list(
		    HttpServletRequest request,
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage, 
			@RequestParam(defaultValue = "20") int numPerPage,
			@RequestParam(value = "orderField", required = false) String orderField, 
			@RequestParam(value = "orderDirection", required = false) String orderDirection,
			BonusEventLogDTO bonusEventLogDTO) throws BusinessException {
		Pager<BonusEventLogDTO> pager = new Pager<>();
		pager.setPageNum(currentPage);
		pager.setCurrentPage(currentPage);
		pager.setNumPerPage(numPerPage);
		pager.setOrderField(orderField);
		pager.setOrderDirection(orderDirection);
		pager.setParams(bonusEventLogDTO);
		bonusEventLogService.selectTList(pager);
		Map<String, Object> map = new HashMap<>();
		if (pager.getList() != null && pager.getList().size() > 0) {
			map.put("list", pager.getList());
			map.put("totalPage", pager.getTotalPage());
			map.put("pageSize", pager.getPageSize());
			map.put("currentPage", pager.getCurrentPage());
		}
		return success(request,map);
	}

	/**
	 * 保存
	 */
	@ApiOperation(value = "添加", notes = "添加积分表")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "id", value = "Id", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "userId", value = "user.id", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "value", value = "积分操作值", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "event", value = "发生的事件", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
		@ApiImplicitParam(name = "description", value = "描述", dataType = "string", paramType = "query"),
	})
	@RequestMapping(value = SAVE, method = RequestMethod.POST)
	public WebResout save(
					HttpServletRequest request,
					BonusEventLogDTO bonusEventLogDTO) throws BusinessException {
		int i = bonusEventLogService.insert(bonusEventLogDTO);
		if (i > 0) {
			return success(request);
		}
		return fail(request,"添加失败");
	}

	/**
	 * 删除
	 */
	@ApiOperation(value = "删除", notes = "删除积分表")
	@ApiImplicitParams({@ApiImplicitParam(name = "id", value = "积分表id", required = true, dataType = "long", paramType = "query") })
	@RequestMapping(value = DELETE, method = RequestMethod.POST)
	public WebResout delete(
			HttpServletRequest request,
			@RequestParam(value = "id", defaultValue = "0") int id) throws BusinessException {
		try {
			bonusEventLogService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return fail(request,"删除失败");
		}
		return success(request);
	}
	/**
	 * 查看
	 */
	@ApiOperation(value = "查看", notes = "根据id查看积分表",response=BonusEventLog.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "积分表id", required = true, dataType = "long", paramType = "query") })
	@RequestMapping(value = VIEW, method = RequestMethod.GET)
	public WebResout view(
			HttpServletRequest request,
			@RequestParam(value = "id", defaultValue = "0") int id) throws BusinessException {
		
		BonusEventLogDTO bonusEventLog = bonusEventLogService.selectById(id);
		if (bonusEventLog != null) {
			return success(request,bonusEventLog);
		}
		return fail(request,"数据不存在或已被删除");
	}

	/**
	 * 更新
	 */
	@ApiOperation(value = "更新", notes = "根据id更新积分表")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "id",required = true, value = "Id", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "userId", value = "user.id", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "value", value = "积分操作值", dataType = "long", paramType = "query"),
		@ApiImplicitParam(name = "event", value = "发生的事件", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
		@ApiImplicitParam(name = "description", value = "描述", dataType = "string", paramType = "query"),
	})
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	public WebResout update(
		HttpServletRequest request,
		@ModelAttribute BonusEventLogDTO bonusEventLog) throws BusinessException {
		int i = bonusEventLogService.updateObj(bonusEventLog);
		if (i > 0) {
			return success(request,"修改成功");
		}
		return fail(request,"修改失败");
	}

}
