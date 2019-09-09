package com.leh.aop.service;

import com.leh.model.Member;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final static Logger LOG = Logger.getLogger(AuthService.class);

    public void login(String loginName, String loginPass) {
        LOG.info("用户登录");
        return;
    }

    public Member login(int name, String loginPass) {
        LOG.info("用户登录");
        return null;
    }

    public Member login(String name, int loginPass) {
        LOG.info("用户登录");
        return null;
    }

    public void logout(String loginName) {
        LOG.info("注销登录");
        return;
    }


}
