package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.UserAuth;
import com.stylefeng.guns.modular.system.dao.UserAuthMapper;
import com.stylefeng.guns.modular.system.service.IUserAuthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
