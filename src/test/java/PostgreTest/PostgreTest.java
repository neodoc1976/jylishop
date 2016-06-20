package PostgreTest;

import org.george.jylishop.domain.Product;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Yulya on 19.06.2016.
 */
public class PostgreTest {

    @Test
    public void postgreTest() {

//        private DataSource source;


        List<Product> list = new ArrayList<>();

        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        //Имя пользователя БД
        String name = "postgres";
        //Пароль
        String password = "java9900";
        try {
            //Загружаем драйвер
            Class.forName("org.postgresql.Driver");
            System.out.println("Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("Соединение установлено");
            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement = null;

            statement = connection.createStatement();
            //Выполним запрос
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM \"Product\"");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Выводим product");
            while (result1.next()) {
                Product product = new Product();
                product.setPrice(result1.getDouble("price"));
                product.setTitle(result1.getString("title"));
                product.setPicture(result1.getString("picture"));
                product.setDescription(result1.getString("description"));
                list.add(product);
                System.out.println("Виборка # " + result1.getRow()
                        + "\t Ціна " + result1.getDouble("price")
                        + "\t Назва " + result1.getString("title")
                        + "\t Опис " + result1.getString("description"));

            }







                /*
                // Вставить запись
                statement.executeUpdate(
                        "INSERT INTO users(username) values('name')");
                //Обновить запись
                statement.executeUpdate(
                        "UPDATE users SET username = 'admin' where id = 1");



                //2.PreparedStatement: предварительно компилирует запросы,
                //которые могут содержать входные параметры
                PreparedStatement preparedStatement = null;
                // ? - место вставки нашего значеня
                preparedStatement = connection.prepareStatement(
                        "SELECT * FROM users where id > ? and id < ?");
                //Устанавливаем в нужную позицию значения определённого типа
                preparedStatement.setInt(1, 2);
                preparedStatement.setInt(2, 10);
                //выполняем запрос
                ResultSet result2 = preparedStatement.executeQuery();

                System.out.println("Выводим PreparedStatement");
                while (result2.next()) {
                    System.out.println("Номер в выборке #" + result2.getRow()
                            + "\t Номер в базе #" + result2.getInt("id")
                            + "\t" + result2.getString("username"));
                }

                preparedStatement = connection.prepareStatement(
                        "INSERT INTO users(username) values(?)");
                preparedStatement.setString(1, "user_name");
                //метод принимает значение без параметров
                //темже способом можно сделать и UPDATE
                preparedStatement.executeUpdate();



                //3.CallableStatement: используется для вызова хранимых функций,
                // которые могут содержать входные и выходные параметры
                CallableStatement callableStatement = null;
                //Вызываем функцию myFunc (хранится в БД)
                callableStatement = connection.prepareCall(
                        " { call myfunc(?,?) } ");
                //Задаём входные параметры
                callableStatement.setString(1, "Dima");
                callableStatement.setString(2, "Alex");
                //Выполняем запрос
                ResultSet result3 = callableStatement.executeQuery();
                //Если CallableStatement возвращает несколько объектов ResultSet,
                //то нужно выводить данные в цикле с помощью метода next
                //у меня функция возвращает один объект
                result3.next();
                System.out.println(result3.getString("MESSAGE"));
                //если функция вставляет или обновляет, то используется метод executeUpdate()
*/
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }


    }
}