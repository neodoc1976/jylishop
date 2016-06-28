package org.george.jylishop.db;

import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yulya on 02.05.2016.
 */
@Component
public class DataBase {
    @Autowired
    private DataSource dataSource;
    private Map<Integer, Product> catalogue = new HashMap<>();

    //    public List<Product> getCatalogue() {
//
//        List<Product> list = new ArrayList<>(catalogue.values());
//        Connection connection = null;
//
//        try {
//            connection = dataSource.getConnection();
//            System.out.println("Соединение установлено");
//            //Для использования SQL запросов существуют 3 типа объектов:
//            //1.Statement: используется для простых случаев без параметров
//            Statement statement = connection.createStatement();
//            //Выполним запрос
//            ResultSet result1 = statement.executeQuery(
//                    "SELECT * FROM \"Product\"");
//            //result это указатель на первую строку с выборки
//            //чтобы вывести данные мы будем использовать
//            //метод next() , с помощью которого переходим к следующему элементу
//            System.out.println("Выводим product");
//            while (result1.next()) {
//                Product product = new Product();
//                product.setId(result1.getInt("id"));
//                product.setPrice(result1.getDouble("price"));
//                product.setTitle(result1.getString("title"));
//                product.setPicture(result1.getString("picture"));
//                product.setDescription(result1.getString("description"));
//                list.add(product);
//                System.out.println("Виборка # " + result1.getRow()
//                        + "\t Ціна " + result1.getDouble("price")
//                        + "\t Назва " + result1.getString("title")
//                        + "\t Опис " + result1.getString("description"));
//            }
//
//        } catch (Exception ex) {
//            //выводим наиболее значимые сообщения
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    System.out.println(ex.getMessage());
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        return list;
//    }
    public Product getProductById(int id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id " +
                "WHERE p.id=?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
    }

    public void addProduct(Product product) {
        if (product instanceof Hemostatic) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO \"Product\" (title, price, description, picture, volume, hemostatic_substance, product_type) " +
                    "VALUES (?,?,?,?,?,?,?)"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((Hemostatic) product).getVolume(),
                    ((Hemostatic) product).getHemostaticSubstance(),
                    Product.HEMO_TYPE);
        }
        if (product instanceof OpalescenseGel) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO \"Product\" (title, price, description, picture, volume,reactant_percent , product_type) " +
                    "VALUES (?,?,?,?,?,?,?)"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((OpalescenseGel) product).getVolume(),
                    ((OpalescenseGel) product).getReactantPercent(),
                    Product.GEL_TYPE);
        }
    }

    public void updateProduct(Product product) {

        Product productInBase = getProductById(product.getId());

        if (productInBase == null) {
            return;
        }
        if (!product.getClass().equals(productInBase.getClass())) {
            return;
        }
        if (product instanceof Hemostatic) {

            String sql = "UPDATE \"Product\" " +
                    "SET title = ?, price = ?,description =?,picture=?,volume=?,hemostatic_substance=? " +
                    "WHERE id=?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((Hemostatic) product).getVolume(),
                    ((Hemostatic) product).getHemostaticSubstance(),
                    product.getId());
        }
        if (product instanceof OpalescenseGel) {

            String sql = "UPDATE \"Product\" " +
                    "SET title = ?, price = ?,description =?,picture=?,volume=?,reactant_percent=? " +
                    "WHERE id=?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((OpalescenseGel) product).getVolume(),
                    ((OpalescenseGel) product).getReactantPercent(),
                    product.getId());
        }
    }

    public void deleteProduct(Product product) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE  FROM \"Product\" WHERE id=?";
        jdbcTemplate.update(sql, product.getId());
    }

    public List<Product> getCatalogueOrderByTitle() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id  ORDER BY title  ";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;
    }

    public List<Product> getCatalogueOrderByPriceAsc() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id  ORDER BY price";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;
    }

    public List<Product> getCatalogueOrderByPriceDesc() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id  ORDER BY price DESC ";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;
    }

    public List<Product> getCatalogueOrderByManufacturer(){

        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        String sql="SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id  ORDER BY m.name ";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;

    }
    public List<Product> getCatalogueOrderByManufacturerReverse(){

        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        String sql="SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id  ORDER BY m.name DESC ";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;

    }

    public List<Product> getCatalogueOrderById() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id  ORDER BY p.id ";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;
    }

    public List<Product> getCatalogue() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT p.id,p.product_type, p.title , p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                " FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id ";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());

        return list;
    }

    public Manufacturer getManufacturerById(int id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT logo, id , name , description  FROM \"Manufacturer\"  WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ManufacturerRowMapper());

    }


}
