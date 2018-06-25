package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.UserAuth;
import com.stylefeng.guns.modular.system.service.IUserAuthService;

/**
 * 授权控制器
 *
 * @author fengshuonan
 * @Date 2018-06-17 15:41:54
 */
@Controller
@RequestMapping("/userAuth")
public class UserAuthController extends BaseController {

    private String PREFIX = "/system/userAuth/";

    @Autowired
    private IUserAuthService userAuthService;

    /**
     * 跳转到授权首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userAuth.html";
    }

    /**
     * 跳转到添加授权
     */
    @RequestMapping("/userAuth_add")
    public String userAuthAdd() {
        return PREFIX + "userAuth_add.html";
    }

    /**
     * 跳转到修改授权
     */
    @RequestMapping("/userAuth_update/{userAuthId}")
    public String userAuthUpdate(@PathVariable Integer userAuthId, Model model) {
        UserAuth userAuth = userAuthService.selectById(userAuthId);
        model.addAttribute("item",userAuth);
        LogObjectHolder.me().set(userAuth);
        return PREFIX + "userAuth_edit.html";
    }

    /**
     * 获取授权列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return userAuthService.selectList(null);
    }

    /**
     * 新增授权
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserAuth userAuth) {
        userAuthService.insert(userAuth);
        return SUCCESS_TIP;
    }

    /**
     * 删除授权
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userAuthId) {
        userAuthService.deleteById(userAuthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改授权
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserAuth userAuth) {
        userAuthService.updateById(userAuth);
        return SUCCESS_TIP;
    }

    /**
     * 授权详情
     */
    @RequestMapping(value = "/detail/{userAuthId}")
    @ResponseBody
    public Object detail(@PathVariable("userAuthId") Integer userAuthId) {
        return userAuthService.selectById(userAuthId);
    }
}
