package com.leh.dao.impl;

import com.leh.dao.UserDao;
import com.leh.vo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: leh
 * @Date: 2019/10/15 15:17
 * @Description:
 */
@Component
public class UserDaoImpl implements UserDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public String getPasswordByUserNameFromDB(String userName) {
        String sql = "select * from dh_user where username = ?";
        List<User> list = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<User>() {

            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).getPassword();
    }

    public Set<String> getRolesByUserNameFromDB(String userName) {
        String sql = "select rolename from dh_user_role where username = ?";
        List<String> roles = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("rolename");
            }
        });
        return new HashSet<String>(roles);
    }

    public Set<String> getPermissionsByUserNameFromDB(String userName) {
        String sql = "select permission from dh_role_permission where rolename = ?";

        List<String> permissions = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("permission");
            }
        });
        return new HashSet<String>(permissions);
    }
}
