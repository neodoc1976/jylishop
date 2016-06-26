package org.george.jylishop.db;

import org.george.jylishop.domain.Contact;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yulya on 27.05.2016.
 */
@Component
public class ContactBase {
    @Autowired
    private DataSource dataSource;

    public List<Contact> getContact() {


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT*FROM  \"contact\" ";
        List<Contact> contactList = jdbcTemplate.query(sql, new ContactRowMapper());

        return contactList;
    }
}
