package org.george.jylishop.db;

import org.george.jylishop.domain.Hemostatic;
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
        if (type.equals(Product.Hemo_Type)) {
            Hemostatic product = new Hemostatic();
            product.setId(resultSet.getInt("id"));
            product.setPrice(resultSet.getDouble("price"));
            product.setTitle(resultSet.getString("title"));
            product.setPicture(resultSet.getString("picture"));
            product.setDescription(resultSet.getString("description"));
            product.setVolume(resultSet.getDouble("volume"));
            product.setHemostaticSubstance(resultSet.getString("hemostatic_substance"));
            return product;
        }

        if (type.equals(Product.Gel_Type)) {
            OpalescenseGel product = new OpalescenseGel();
            product.setId(resultSet.getInt("id"));
            product.setPrice(resultSet.getDouble("price"));
            product.setTitle(resultSet.getString("title"));
            product.setPicture(resultSet.getString("picture"));
            product.setDescription(resultSet.getString("description"));
            product.setVolume(resultSet.getDouble("volume"));
            product.setReactantPercent(resultSet.getDouble("reactant_percent"));
            return product;
        }

        throw new SQLException("Wrong type");
    }

}
