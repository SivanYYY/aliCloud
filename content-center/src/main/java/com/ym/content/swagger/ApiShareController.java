package com.ym.content.swagger;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ym.content.commons.exceptions.BusinessException;
import com.ym.content.commons.page.Pager;
import com.ym.content.commons.remsg.WebResout;
import com.ym.content.config.ApiBaseController;
import com.ym.content.domain.Share;
import com.ym.content.domain.dto.ShareDTO;
import com.ym.content.service.IShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.client.RestTemplate;

@Api(value = "shareApi", description = "分享表对外接口")
@RestController
@RequestMapping("/api//share/1.0")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiShareController extends ApiBaseController {

    @Autowired
    private  IShareService shareService;

    /**
     * 分页查看
     */
    @ApiOperation(value = "列表", notes = "查询分享表列表", response = Share.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "没有查找到分享表")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "numPerPage", value = "每页显示条数，默认20条", defaultValue = "20", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderField", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "orderDirection", value = "排序方式 ASC|DESC", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "发布人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "isOriginal", value = "是否原创 0:否 1:是", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "author", value = "作者", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cover", value = "封面", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "概要信息", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "price", value = "价格（需要的积分）", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "downloadUrl", value = "下载地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "buyCount", value = "下载数 ", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "showFlag", value = "是否显示 0:否 1:是", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "auditStatus", value = "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public WebResout list(
            HttpServletRequest request,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "20") int numPerPage,
            @RequestParam(value = "orderField", required = false) String orderField,
            @RequestParam(value = "orderDirection", required = false) String orderDirection,
            ShareDTO shareDTO) throws BusinessException {
        Pager<ShareDTO> pager = new Pager<>();
        pager.setPageNum(currentPage);
        pager.setCurrentPage(currentPage);
        pager.setNumPerPage(numPerPage);
        pager.setOrderField(orderField);
        pager.setOrderDirection(orderDirection);
        pager.setParams(shareDTO);
        shareService.selectTList(pager);
        Map<String, Object> map = new HashMap<>();
        if (pager.getList() != null && pager.getList().size() > 0) {
            map.put("list", pager.getList());
            map.put("totalPage", pager.getTotalPage());
            map.put("pageSize", pager.getPageSize());
            map.put("currentPage", pager.getCurrentPage());
        }
        return success(request, map);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "添加", notes = "添加分享表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "发布人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "isOriginal", value = "是否原创 0:否 1:是", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "author", value = "作者", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cover", value = "封面", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "概要信息", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "price", value = "价格（需要的积分）", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "downloadUrl", value = "下载地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "buyCount", value = "下载数 ", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "showFlag", value = "是否显示 0:否 1:是", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "auditStatus", value = "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = SAVE, method = RequestMethod.POST)
    public WebResout save(
            HttpServletRequest request,
            ShareDTO shareDTO) throws BusinessException {
        int i = shareService.insert(shareDTO);
        if (i > 0) {
            return success(request);
        }
        return fail(request, "添加失败");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "删除分享表")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "分享表id", required = true, dataType = "long", paramType = "query")})
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public WebResout delete(
            HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "0") int id) throws BusinessException {
        try {
            shareService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(request, "删除失败");
        }
        return success(request);
    }

    /**
     * 查看
     */
    @ApiOperation(value = "查看", notes = "根据id查看分享表", response = Share.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "分享表id", required = true, dataType = "long", paramType = "query")})
    @RequestMapping(value = VIEW, method = RequestMethod.GET)
    public WebResout view(
            HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "0") int id) throws BusinessException {

        ShareDTO share = shareService.selectById(id);
        if (share != null) {
            return success(request, share);
        }
        return fail(request, "数据不存在或已被删除");
    }

    /**
     * 更新
     */
    @ApiOperation(value = "更新", notes = "根据id更新分享表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "发布人id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "标题", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "isOriginal", value = "是否原创 0:否 1:是", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "author", value = "作者", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "cover", value = "封面", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "概要信息", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "price", value = "价格（需要的积分）", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "downloadUrl", value = "下载地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "buyCount", value = "下载数 ", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "showFlag", value = "是否显示 0:否 1:是", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "auditStatus", value = "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", dataType = "string", paramType = "query"),
    })
    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public WebResout update(
            HttpServletRequest request,
            @ModelAttribute ShareDTO share) throws BusinessException {
        int i = shareService.updateObj(share);
        if (i > 0) {
            return success(request, "修改成功");
        }
        return fail(request, "修改失败");
    }

//    public static void main(String[] args) {
//        RestTemplate restTemplate = new RestTemplate();
//		String forObject = restTemplate.getForObject("http://localhost:9526/id", String.class);
//		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:9526/id", String.class);
//	}

}
