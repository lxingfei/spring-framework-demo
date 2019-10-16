package com.leh.dao;

import java.util.Set;

/**
 * @Auther: leh
 * @Date: 2019/10/15 15:17
 * @Description:
 */
public interface UserDao {

    String getPasswordByUserNameFromDB(String userName);

    Set<String> getRolesByUserNameFromDB(String userName);

    Set<String> getPermissionsByUserNameFromDB(String userName);

}
