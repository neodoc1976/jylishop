package org.george.jylishop.db;

import org.george.jylishop.domain.Hemostatic;
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
//
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

        String sql = "SELECT * FROM  \"Product\" WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ProductRowMapper());
    }


    public void addProduct(Product product) {
        if (product instanceof Hemostatic) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO \"Product\" (title, price, description, picture, volume, hemostatic_substance, product_type) " +
                    "VALUES (?,?,?,?,?,?,'hemostatic')"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((Hemostatic) product).getVolume(),
                    ((Hemostatic) product).getHemostaticSubstance());
        }
        if (product instanceof OpalescenseGel) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "INSERT INTO \"Product\" (title, price, description, picture, volume,reactant_percent , product_type) " +
                    "VALUES (?,?,?,?,?,?,'opal_gel')"; // Назва колонок в таблиці бази та відповідні їх значення впід VALUES
            jdbcTemplate.update(sql, product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getPicture(),
                    ((OpalescenseGel) product).getVolume(),
                    ((OpalescenseGel) product).getReactantPercent());
        }

    }

    public void updatePtroduct(Product product) {


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

    public List<Product> getCatalogue() {


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "SELECT * FROM \"Product\"";
        List<Product> list = jdbcTemplate.query(sql, new ProductRowMapper());


        return list;
    }

}
//    public DataBase() {
//        OpalescenseGel first = new OpalescenseGel();
//        first.setTitle("Opalescense Gel PF");
//        first.setReactantPercent(10);
//        first.setVolume(1.2);
//        first.setDescription("Whitening gel for home whitening.");
//        first.setPrice(5);
//        first.setPicture("first.jpg");
//        first.setId(101);
//        catalogue.put(first.getId(),first);
//
//        OpalescenseGel second = new OpalescenseGel();
//        second.setTitle("Opalescense Gel PF");
//        second.setReactantPercent(15);
//        second.setVolume(1.2);
//        second.setDescription("Whitening gel for home whitening.");
//        second.setPrice(6);
//        second.setPicture("second.jpg");
//        second.setId(202);
//        catalogue.put(second.getId(),second);
//
//        OpalescenseGel third = new OpalescenseGel();
//        third.setTitle("Opalescense Gel PF");
//        third.setReactantPercent(20);
//        third.setVolume(1.2);
//        third.setDescription("Whitening gel for home whitening.");
//        third.setPrice(7);
//        third.setPicture("third.jpg");
//        third.setId(303);
//        catalogue.put(third.getId(),third);
//
//        Hemostatic viscosyringe = new Hemostatic();
//        viscosyringe.setTitle("ViscoStat");
//        viscosyringe.setHemostaticSubstance("Ferric Sulphate");
//        viscosyringe.setDescription("ViscoStat hemostatic is a 20% ferric sulfate equivalent solution with inert binding agents in a viscous, aqueous vehicle. ");
//        viscosyringe.setVolume(1.2);
//        viscosyringe.setPrice(4.8);
//        viscosyringe.setId(404);
//        viscosyringe.setPicture("visco.jpg");
//        catalogue.put(viscosyringe.getId(),viscosyringe);
//
//        Hemostatic viscoclearsyringe = new Hemostatic();
//        viscoclearsyringe.setTitle("ViscoStat Clear");
//        viscoclearsyringe.setHemostaticSubstance("Aluminum Chloride");
//        viscoclearsyringe.setDescription("ViscoStat Clear is recommended for anterior restorations because it quickly eliminates minor bleeding without leaving any residue.");
//        viscoclearsyringe.setVolume(1.2);
//        viscoclearsyringe.setPrice(5.04);
//        viscoclearsyringe.setId(505);
//        viscoclearsyringe.setPicture("viscoclear.jpg");
//        catalogue.put(viscoclearsyringe.getId(),viscoclearsyringe);
//
//        Hemostatic viscodispenser = new Hemostatic();
//        viscodispenser.setTitle("ViscoStat");
//        viscodispenser.setHemostaticSubstance("Ferric Sulphate");
//        viscodispenser.setDescription("ViscoStat hemostatic is a 20% ferric sulfate equivalent solution with inert binding agents in a viscous, aqueous vehicle.");
//        viscodispenser.setVolume(30);
//        viscodispenser.setPrice(43.3);
//        viscodispenser.setId(606);
//        viscodispenser.setPicture("viscobig.jpg ");
//        catalogue.put(viscodispenser.getId(),viscodispenser);
//
//        Hemostatic viscocleardispenser = new Hemostatic();
//        viscocleardispenser.setTitle("ViscoStat Clear");
//        viscocleardispenser.setHemostaticSubstance("Aluminuim Chloride");
//        viscocleardispenser.setDescription("ViscoStat Clear is recommended for anterior restorations because it quickly eliminates minor bleeding without leaving any residue.");
//        viscocleardispenser.setVolume(30);
//        viscocleardispenser.setPrice(45.4);
//        viscocleardispenser.setId(707);
//        viscocleardispenser.setPicture("viscoclearbig.jpg");
//        catalogue.put(viscocleardispenser.getId(),viscocleardispenser);
//    }

//}
