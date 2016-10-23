package org.george.jylishop.db;

import org.george.jylishop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 23.10.2016.
 */
@Component
public class UserBase {
    @Autowired
    private DataSource dataSource;

    private SqlRowSet getResultSetForUser(String username) {

        JdbcTemplate jd = new JdbcTemplate(dataSource);
        String querry = ("SELECT * FROM users u " +
                "JOIN user_roles r ON u.username=r.username " +
                "WHERE u.username=?");

        SqlRowSet result;
        result = jd.queryForRowSet(querry);

        return result;
    }

    private User extractUser(SqlRowSet result) {

        User user = new User();
        List<String> list = new ArrayList<>();

        while (result.next()) {
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
            user.setEnabled(result.getBoolean("enabled"));
            list.add(result.getString("role"));
        }

        user.setRoles(list);

        return user;
    }

    public User getUserInfo(String username) {

        return extractUser(getResultSetForUser(username));
    }

    public void storeNewUser(User user) {

        JdbcTemplate jd = new JdbcTemplate(dataSource);

        String insert = "INSERT INTO \"users\" (username,password,enabled) VALUES (?,?,?);";
        jd.update(insert,
                user.getUsername(),
                user.getPassword(),
                user.getEnabled() ? 1 : 0);

        JdbcTemplate jd__for_role = new JdbcTemplate(dataSource); // Because "ROLE_USER" have multiple values is on the list
        String insert_roles = "INSERT INTO \"user_roles\" (username,role) VALUES (?,?);";

        for (String role : user.getRoles()) { //Перебираємо всі значення ролей для користувача.

            jd__for_role.update(insert_roles, user.getUsername(), role);

        }


    }


}
