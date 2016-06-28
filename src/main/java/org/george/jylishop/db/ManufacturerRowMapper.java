package org.george.jylishop.db;

import org.george.jylishop.domain.Manufacturer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yulya on 26.06.2016.
 */
public class ManufacturerRowMapper implements RowMapper<Manufacturer> {
    @Override
    public Manufacturer mapRow(ResultSet resultSet, int i) throws SQLException {

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt("id"));
        manufacturer.setName(resultSet.getString("name"));
        manufacturer.setDescription(resultSet.getString("description"));
        manufacturer.setLogo(resultSet.getString("logo"));
        return manufacturer;
    }


}


