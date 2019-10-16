package com.leh.controller;

import com.leh.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: leh
 * @Date: 2019/10/15 10:32
 * @Description:
 */
@Controller
public class UserController {

    /*
        如果请求中接收到参数没有乱码，但是返回json字符串时候乱码了，
        1) 可以添加produce解决
        2) 每个方法加produce解决未免过于麻烦，那么可以修改转换器的编码处理，
           处理String的转换器为StringHttpMessageConverter，查看源码发现默认的编码是ISO-8859-1
           springmvc配置文件中统一修改：
           <!-- 注解驱动 -->
            <mvc:annotation-driven>
                <!-- 处理请求返回json字符串的中文乱码问题 -->
                <mvc:message-converters>
                    <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                        <constructor-arg value="UTF-8" />
                    </bean>
                </mvc:message-converters>
            </mvc:annotation-driven>
    */
    @RequestMapping(value = "subLogin", method = RequestMethod.POST,
            /*这里顺序不能反，一定先写text/html,不然ie下出现下载提示, firefox json解析出错*/
            produces = {"text/html;charset=UTF-8", "application/json;charset=UTF-8", "text/plain;charset=UTF-8"})
    @ResponseBody
    public String subLogin(User user){
        //获取主体 提交认证请求
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        //设置是否将cookie存储到客户端，实现rememberMe直接登录
        token.setRememberMe(user.isRememberMe());
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return e.getMessage();
        }

        /*编程方式 授权*/
        if(subject.hasRole("admin") && subject.isPermittedAll("user:update","user:delete")){
            return "恭喜你，登录成功，有admin角色，有修改用户权限";
        }
        /* TODO 注解方式授权 */
        return "恭喜你，登录成功";
    }


    @RequiresRoles("admin")
    @RequestMapping(value = "testAnnotationRole", method = RequestMethod.GET)
    @ResponseBody
    public String testAnnotationRole(User user){
        return "用户：" + user.getUserName() + ",有admin角色";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "testAnnotationPermission", method = RequestMethod.GET)
    @ResponseBody
    public String testAnnotationPermission(User user){
        return "用户：" + user.getUserName() + ",有删除用户权限";
    }


    @RequestMapping(value = "testRole", method = RequestMethod.GET)
    @ResponseBody
    public String testRole(User user){
        return "testRole OK";
    }


    @RequestMapping(value = "testRole1", method = RequestMethod.GET)
    @ResponseBody
    public String testRole1(User user){
        return "testRole1 OK";
    }

    /**
     * 测试自定义的filter
     * @param user
     * @return
     */
    @RequestMapping(value = "testCustomRole", method = RequestMethod.GET)
    @ResponseBody
    public String testCustomRole(User user){
        return "testCustomRole OK";
    }

    @RequestMapping(value = "testPerm", method = RequestMethod.GET)
    @ResponseBody
    public String testPerm(User user){
        return "testPerm OK";
    }

    @RequestMapping(value = "testPerm1", method = RequestMethod.GET)
    @ResponseBody
    public String testPerm1(User user){
        return "testPerm1 OK";
    }


}
