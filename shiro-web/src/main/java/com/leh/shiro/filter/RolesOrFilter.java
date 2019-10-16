package com.leh.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Auther: leh
 * @Date: 2019/10/15 17:52
 * @Description:
 */
public class RolesOrFilter extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse
            servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);

        String[] roles = (String[]) o;

        if (roles == null || roles.length == 0) {
            return false;
        }

        for (int i = 0; i < roles.length; i++) {
            if (subject.hasRole(roles[i])) {
                return true;
            }
        }
        return false;
    }
}
