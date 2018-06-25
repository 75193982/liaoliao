package com.stylefeng.guns.modular.system.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;

import org.springframework.web.bind.annotation.RequestParam;

import com.stylefeng.guns.modular.system.model.MUsers;
import com.stylefeng.guns.modular.system.service.IMUsersService;

/**
 * 用户管理表控制器
 *
 * @author fengshuonan
 * @Date 2018-06-16 17:12:41
 */
@Controller
@RequestMapping("/mUsers")
public class MUsersController extends BaseController {

    private String PREFIX = "/system/mUsers/";

    @Autowired
    private IMUsersService mUsersService;

    /**
     * 跳转到用户管理表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mUsers.html";
    }

    /**
     * 跳转到添加用户管理表
     */
    @RequestMapping("/mUsers_add")
    public String mUsersAdd() {
        return PREFIX + "mUsers_add.html";
    }

    /**
     * 跳转到修改用户管理表
     */
    @RequestMapping("/mUsers_update/{mUsersId}")
    public String mUsersUpdate(@PathVariable Integer mUsersId, Model model) {
        MUsers mUsers = mUsersService.selectById(mUsersId);
        model.addAttribute("item",mUsers);
        LogObjectHolder.me().set(mUsers);
        return PREFIX + "mUsers_edit.html";
    }

    /**
     * 获取用户管理表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return mUsersService.selectList(null);
    }

    /**
     * 新增用户管理表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MUsers mUsers) {
        mUsersService.insert(mUsers);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户管理表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mUsersId) {
        mUsersService.deleteById(mUsersId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户管理表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MUsers mUsers) {
        mUsersService.updateById(mUsers);
        return SUCCESS_TIP;
    }

    /**
     * 用户管理表详情
     */
    @RequestMapping(value = "/detail/{mUsersId}")
    @ResponseBody
    public Object detail(@PathVariable("mUsersId") Integer mUsersId) {
        return mUsersService.selectById(mUsersId);
    }
}
