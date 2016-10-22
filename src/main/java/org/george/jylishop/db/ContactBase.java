package org.george.jylishop.db;

import org.george.jylishop.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
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

        String sql = "SELECT*FROM  \"Contact\" ";
        List<Contact> contactList = jdbcTemplate.query(sql, new ContactRowMapper());

        return contactList;
    }

    public Contact getContactById(int id){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM  \"Contact\" WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ContactRowMapper());
    }

    public void updateContact(Contact contact){
        String sql = "UPDATE \"Contact\" " +
                "SET name = ?, email = ?,address =?,telephone=?,location=?" +
                "WHERE id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, contact.getName(),
                contact.getEmail(),
                contact.getAddress(),
                contact.getTelephone(),
                contact.getLocation(),
                contact.getId());

    }
    public void deleteContact(Contact contact) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE  FROM \"Contact\" WHERE id=?";
        jdbcTemplate.update(sql, contact.getId());
    }

    public void addContact(Contact contact) {

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO \"Contact\" (email,address ,telephone,location,name,data_type) " +
                    "VALUES (?,?,?,?,?,?)"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
            jdbcTemplate.update(sql, contact.getEmail(),
                    contact.getAddress(),
                    contact.getTelephone(),
                    contact.getLocation(),
                    contact.getName(),
                    Contact.CONTACT_TYPE);

    }

}
