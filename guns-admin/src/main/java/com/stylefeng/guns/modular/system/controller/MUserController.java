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
import com.stylefeng.guns.modular.system.model.MUser;
import com.stylefeng.guns.modular.system.service.IMUserService;

/**
 * 用户管理表控制器
 *
 * @author fengshuonan
 * @Date 2018-06-16 17:10:58
 */
@Controller
@RequestMapping("/mUser")
public class MUserController extends BaseController {

    private String PREFIX = "/system/mUser/";

    @Autowired
    private IMUserService mUserService;

    /**
     * 跳转到用户管理表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mUser.html";
    }

    /**
     * 跳转到添加用户管理表
     */
    @RequestMapping("/mUser_add")
    public String mUserAdd() {
        return PREFIX + "mUser_add.html";
    }

    /**
     * 跳转到修改用户管理表
     */
    @RequestMapping("/mUser_update/{mUserId}")
    public String mUserUpdate(@PathVariable Integer mUserId, Model model) {
        MUser mUser = mUserService.selectById(mUserId);
        model.addAttribute("item",mUser);
        LogObjectHolder.me().set(mUser);
        return PREFIX + "mUser_edit.html";
    }

    /**
     * 获取用户管理表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mUserService.selectList(null);
    }

    /**
     * 新增用户管理表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MUser mUser) {
        mUserService.insert(mUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户管理表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mUserId) {
        mUserService.deleteById(mUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户管理表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MUser mUser) {
        mUserService.updateById(mUser);
        return SUCCESS_TIP;
    }

    /**
     * 用户管理表详情
     */
    @RequestMapping(value = "/detail/{mUserId}")
    @ResponseBody
    public Object detail(@PathVariable("mUserId") Integer mUserId) {
        return mUserService.selectById(mUserId);
    }
}
