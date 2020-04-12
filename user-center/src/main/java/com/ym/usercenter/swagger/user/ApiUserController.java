package com.ym.usercenter.swagger.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ym.usercenter.commons.remsg.WebResout;
import com.ym.usercenter.config.ApiBaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ym.usercenter.commons.exceptions.BusinessException;
import com.ym.usercenter.commons.page.Pager;
import com.ym.usercenter.domain.user.User;
import com.ym.usercenter.domain.user.dto.UserDTO;
import com.ym.usercenter.service.user.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Sivan
 */
@Api(value = "userApi", description = "用户表对外接口")
@RestController
@RequestMapping("/api/user/1.0")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiUserController extends ApiBaseController {

    private final IUserService userService;

    /**
     * 分页查看
     */
    @ApiOperation(value = "列表", notes = "查询用户表列表", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 404, message = "没有查找到用户表")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", defaultValue = "1", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "numPerPage", value = "每页显示条数，默认20条", defaultValue = "20", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderField", value = "排序字段", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "orderDirection", value = "排序方式 ASC|DESC", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "Id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "wxId", value = "微信id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "wxNickname", value = "微信昵称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "roles", value = "角色", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "avatarUrl", value = "头像地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "bonus", value = "积分", dataType = "long", paramType = "query"),
    })
    @RequestMapping(value = LIST, method = RequestMethod.GET)
    public WebResout list(
            HttpServletRequest request,
            @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "20") int numPerPage,
            @RequestParam(value = "orderField", required = false) String orderField,
            @RequestParam(value = "orderDirection", required = false) String orderDirection,
            UserDTO userDTO) throws BusinessException {
        Pager<UserDTO> pager = new Pager<>();
        pager.setPageNum(currentPage);
        pager.setCurrentPage(currentPage);
        pager.setNumPerPage(numPerPage);
        pager.setOrderField(orderField);
        pager.setOrderDirection(orderDirection);
        pager.setParams(userDTO);
        userService.selectTList(pager);
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
    @ApiOperation(value = "添加", notes = "添加用户表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "wxId", value = "微信id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "wxNickname", value = "微信昵称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "roles", value = "角色", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "avatarUrl", value = "头像地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "bonus", value = "积分", dataType = "long", paramType = "query"),
    })
    @RequestMapping(value = SAVE, method = RequestMethod.POST)
    public WebResout save(
            HttpServletRequest request,
            UserDTO userDTO) throws BusinessException {
        int i = userService.insert(userDTO);
        if (i > 0) {
            return success(request);
        }
        return fail(request, "添加失败");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "删除用户表")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户表id", required = true, dataType = "long", paramType = "query")})
    @RequestMapping(value = DELETE, method = RequestMethod.POST)
    public WebResout delete(
            HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "0") int id) throws BusinessException {
        try {
            userService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return fail(request, "删除失败");
        }
        return success(request);
    }

    /**
     * 查看
     */
    @ApiOperation(value = "查看", notes = "根据id查看用户表", response = User.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户表id", required = true, dataType = "long", paramType = "query")})
    @RequestMapping(value = VIEW, method = RequestMethod.GET)
    public UserDTO view(
            HttpServletRequest request,
            @RequestParam(value = "id", defaultValue = "0") int id) throws BusinessException {
        logger.info("请求进来了：" + System.currentTimeMillis());
        UserDTO user = userService.selectById(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    /**
     * 更新
     */
    @ApiOperation(value = "更新", notes = "根据id更新用户表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "Id", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "wxId", value = "微信id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "wxNickname", value = "微信昵称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "roles", value = "角色", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "avatarUrl", value = "头像地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "createTime", value = "创建时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "updateTime", value = "修改时间", dataType = "date-time", paramType = "query"),
            @ApiImplicitParam(name = "bonus", value = "积分", dataType = "long", paramType = "query"),
    })
    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public WebResout update(
            HttpServletRequest request,
            @ModelAttribute UserDTO user) throws BusinessException {
        int i = userService.updateObj(user);
        if (i > 0) {
            return success(request, "修改成功");
        }
        return fail(request, "修改失败");
    }

    @GetMapping("/q")
    public User query(User user){
        return user;
    }

}
