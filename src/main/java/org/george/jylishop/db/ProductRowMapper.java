package org.george.jylishop.db;

import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yulya on 19.06.2016.
 */
public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {

        String type = resultSet.getString("product_type");

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(resultSet.getInt("manufacturer"));
        manufacturer.setName(resultSet.getString("name"));
        manufacturer.setDescription(resultSet.getString("description"));
        manufacturer.setLogo(resultSet.getString("logo"));

        if (type.equals(Product.HEMO_TYPE)) {
            Hemostatic product = new Hemostatic();
            product.setId(resultSet.getInt("id"));
            product.setPrice(resultSet.getDouble("price"));
            product.setTitle(resultSet.getString("title"));
            product.setPicture(resultSet.getString("picture"));
            product.setDescription(resultSet.getString("description"));
            product.setVolume(resultSet.getDouble("volume"));
            product.setHemostaticSubstance(resultSet.getString("hemostatic_substance"));
            product.setManufacturer(manufacturer);
            return product;
        }

        if (type.equals(Product.GEL_TYPE)) {
            OpalescenseGel product = new OpalescenseGel();
            product.setId(resultSet.getInt("id"));
            product.setPrice(resultSet.getDouble("price"));
            product.setTitle(resultSet.getString("title"));
            product.setPicture(resultSet.getString("picture"));
            product.setDescription(resultSet.getString("description"));
            product.setVolume(resultSet.getDouble("volume"));
            product.setReactantPercent(resultSet.getDouble("reactant_percent"));
            product.setManufacturer(manufacturer);
            return product;
        }

        throw new SQLException("Wrong type");
    }

}
