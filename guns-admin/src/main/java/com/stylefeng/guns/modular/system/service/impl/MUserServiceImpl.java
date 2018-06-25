package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.model.MUser;
import com.stylefeng.guns.modular.system.dao.MUserMapper;
import com.stylefeng.guns.modular.system.service.IMUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-16
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements IMUserService {

}
