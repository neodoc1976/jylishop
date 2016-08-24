package org.george.jylishop.db;

import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
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
            String sql = "INSERT INTO \"Product\" (title, price, description, picture, volume, hemostatic_substance, product_type,manufacturer) " +
                    "VALUES (?,?,?,?,?,?,?,?)"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((Hemostatic) product).getVolume(),
                    ((Hemostatic) product).getHemostaticSubstance(),
                    Product.HEMO_TYPE,
                    product.getManufacturer().getId());
        }
        if (product instanceof OpalescenseGel) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO \"Product\" (title, price, description, picture, volume,reactant_percent , product_type,manufacturer) " +
                    "VALUES (?,?,?,?,?,?,?,?)"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((OpalescenseGel) product).getVolume(),
                    ((OpalescenseGel) product).getReactantPercent(),
                    Product.GEL_TYPE,
                    product.getManufacturer().getId());
        }
    }

    public void addManufacturer(Manufacturer manufacturer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO \"Manufacturer\" (name,logo,description) " +
                "VALUES (?,?,?)"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
        jdbcTemplate.update(sql,
                manufacturer.getName(),
                manufacturer.getLogo(),
                manufacturer.getDescription());

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
                    "SET title = ?, price = ?,description =?,picture=?,volume=?,hemostatic_substance=?,manufacturer=? " +
                    "WHERE id=?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((Hemostatic) product).getVolume(),
                    ((Hemostatic) product).getHemostaticSubstance(),
                    product.getManufacturer().getId(),
                    product.getId());
        }
        if (product instanceof OpalescenseGel) {

            String sql = "UPDATE \"Product\" " +
                    "SET title = ?, price = ?,description =?,picture=?,volume=?,reactant_percent=?,manufacturer=? " +
                    "WHERE id=?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((OpalescenseGel) product).getVolume(),
                    ((OpalescenseGel) product).getReactantPercent(),
                    product.getManufacturer().getId(),
                    product.getId());

        }
    }

    public void updateManufacturer(Manufacturer manufacturer) {
        String sql = "UPDATE \"Manufacturer\" SET name=?,logo=?,description=? WHERE id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, manufacturer.getName(),
                manufacturer.getLogo(),
                manufacturer.getDescription(),
                manufacturer.getId());
    }

    public void deleteProduct(Product product) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE  FROM \"Product\" WHERE id=?";
        jdbcTemplate.update(sql, product.getId());
    }

    public void deleteManufacturer(Manufacturer manufacturer) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM \"Manufacturer\" WHERE id=?";
        jdbcTemplate.update(sql, manufacturer.getId());
    }

    public List<Product> getCatalogueOrderByTitleByAlphabet() {

        return getCatalogueOrderByTitle(" ORDER BY title");
    }

    public List<Product> getCatalogueOrderByTitleReverse() {

        return getCatalogueOrderByTitle(" ORDER BY title DESC");
    }

    private List<Product> getCatalogueOrderByTitle(String sort_type) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id" + sort_type;
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;
    }

    public List<Product> getCatalogueOrderByPriceAsc() {

        return getCatalogueOrderByPrice(" ORDER BY price");
    }

    public List<Product> getCatalogueOrderByPriceDesc() {

        return getCatalogueOrderByPrice(" ORDER BY price DESC");
    }

    private List<Product> getCatalogueOrderByPrice(String sort_type) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id" + sort_type;
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());
        return list;

    }

    public List<Product> getCatalogueOrderByManufacturerByAlphabet() {

        return getCatalogueOrderByManufacturer(" ORDER BY m.name");
    } //Do not forget to put a space at the beginning of String

    public List<Product> getCatalogueOrderByManufacturerReverse() {

        return getCatalogueOrderByManufacturer(" ORDER BY m.name DESC");

    }

    private List<Product> getCatalogueOrderByManufacturer(String sort_type) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = ("SELECT p.id, p.title ,p.product_type, p.price , p.description , p.picture , p.volume," +
                "p.reactant_percent, p.hemostatic_substance,m.name,p.manufacturer,m.description,m.logo " +
                "FROM \"Product\" p " +
                "INNER JOIN \"Manufacturer\" m ON p.manufacturer = m.id" + sort_type);
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

        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    public Manufacturer getManufacturerById(int id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT logo, id , name , description  FROM \"Manufacturer\"  WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ManufacturerRowMapper());

    }

    public List<Manufacturer> getAllManufacturers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT logo, id , name , description FROM \"Manufacturer\" ";
        return jdbcTemplate.query(sql, new ManufacturerRowMapper());
    }

    public List<Product> getProductListByManufacturer(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT*FROM \"Product\" p INNER JOIN \"Manufacturer\" m ON  m.id=p.manufacturer WHERE m.id=?";
        List<Product> list = jdbcTemplate.query(sql, new Object[]{id}, new ProductRowMapper());
        return list;

    }

    public void deleteProductListByManufacturer(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "DELETE FROM \"Product\" WHERE manufacturer=?";
        jdbcTemplate.update(sql, id);
    }

    public void changeManufacturerForProducts(int newId, int oldId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "UPDATE \"Product\"  SET Manufacturer=? WHERE Manufacturer=? ";
        jdbcTemplate.update(sql, newId, oldId);
    }

    public int getProductsCountForManufacturer(int id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = ("SELECT count (*) FROM \"Product\" p WHERE  p.manufacturer=?;");
        int i = jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class);
        return i;

    }

    public List<Product> getOnlyGels() {

        return getOnlyOneTypeOfProduct("'opal_gel'");

    }

    public List<Product> getOnlyHemos() {

        return getOnlyOneTypeOfProduct("'hemostatic'");
    }

    private List<Product> getOnlyOneTypeOfProduct(String product_type) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT*FROM \"Product\" p INNER JOIN \"Manufacturer\" m ON  m.id=p.manufacturer WHERE p.product_type=" + product_type;
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

}