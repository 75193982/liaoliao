package com.stylefeng.guns.rest.liaoliao.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.rest.liaoliao.dao.UserAuthMapper;
import com.stylefeng.guns.rest.liaoliao.service.IUserAuthService;
import com.stylefeng.guns.rest.model.UserAuth;

/**
 * <p>
 * 授权表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-17
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {

}
