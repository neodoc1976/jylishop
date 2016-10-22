package org.george.jylishop.db;

import org.george.jylishop.domain.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yulya on 22.06.2016.
 */
public class ContactRowMapper implements RowMapper<Contact>{

    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException{

        String type = resultSet.getString("data_type");

        if (type.equals(Contact.CONTACT_TYPE)) {

            Contact contact=new Contact();
            contact.setId(resultSet.getInt("id"));
            contact.setName(resultSet.getString("name"));
            contact.setEmail(resultSet.getString("email"));
            contact.setAddress(resultSet.getString("address"));
            contact.setTelephone(resultSet.getString("telephone"));
            contact.setLocation(resultSet.getString("location"));
            return  contact;
        }

        throw new SQLException("Wrong type");
    }
}
