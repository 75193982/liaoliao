package com.stylefeng.guns.rest.modular.auth.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.liaoliao.service.IUserAuthService;
import com.stylefeng.guns.rest.model.MUsers;
import com.stylefeng.guns.rest.model.UserAuth;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.util.SigatureUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleValidator")
    private IReqValidator reqValidator;

    
    @Autowired
    private IUserAuthService userAuthService ;
   
    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseEntity<?> createAuthenticationToken(AuthRequest authRequest) {
    	String userName = authRequest.getUserName();
    	String type = authRequest.getType();
       
        if( null != userName){
        	 boolean validate = reqValidator.validate(authRequest);
        	 if (validate) {
                 final String randomKey = jwtTokenUtil.getRandomKey();
                 final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
                 String sign = SigatureUtil.getSign(userName);
                 return ResponseEntity.ok(new AuthResponse(token, randomKey,sign));
             } else {
                 throw new GunsException(BizExceptionEnum.AUTH_REQUEST_ERROR);
             }
        	
        }else{
        	
        	Wrapper<UserAuth> wrapper = new EntityWrapper<UserAuth>().eq("token",authRequest.getToken()).eq("type", authRequest.getType()).eq("user_id", authRequest.getUserId());
        	UserAuth userAuth = userAuthService.selectOne( wrapper );
        	if( null == userAuth ){
        		userAuth = new UserAuth();
        		userAuth.setGender(authRequest.getGender());
        		userAuth.setHeadImageUrl(authRequest.getHeadImageUrl());
        		userAuth.setName(authRequest.getName());
        		userAuth.setToken(authRequest.getToken());
        		userAuth.setType(type);
        		userAuth.setUserId(authRequest.getUserId());
        		userAuthService.insert(userAuth);
        	}
        	 String sign = SigatureUtil.getSign(authRequest.getUserId());
        	 final String randomKey = jwtTokenUtil.getRandomKey();
        	 final String token = jwtTokenUtil.generateToken(authRequest.getToken(), randomKey);
             return ResponseEntity.ok(new AuthResponse(token, randomKey,sign));
        }
       
    }
}
