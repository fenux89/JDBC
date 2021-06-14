package homework2.dao;




import homework2.connection.Connector;
import homework2.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public class CarJDBCDao implements CarDAO {

    @Override
    public void add(Car car) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {

            int markId = getMarkId(car.getMark(), connection);

            if (markId == -1) {
                statement = connection.prepareStatement("INSERT INTO marks(mark) VALUES (?)");
                statement.setString(1, car.getMark());

                statement.execute();

                statement = connection.prepareStatement("SELECT MAX(id) FROM marks");

                ResultSet rs = statement.executeQuery();

                rs.next();

                markId = rs.getInt(1);
            }

            statement = connection.prepareStatement("INSERT INTO cars(mark_id, model, price) VALUES (?, ?, ?)");

            statement.setInt(1, markId);
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getPrice());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

    }

    private int getMarkId(String markName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM marks WHERE mark = ? ");
            preparedStatement.setString(1, markName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public List<Car> getAll() {
        List<Car> allCars = new ArrayList<>();
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT cars.id, marks.mark, cars.model, cars.price FROM cars " +
                    "INNER JOIN marks ON marks.id = cars.mark_id;");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Car car = new Car(
                        rs.getLong("id"),
                        rs.getString("mark"),
                        rs.getString("model"),
                        rs.getInt("price")
                );

                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

        return allCars;
    }

    @Override
    public Car getById(int id) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT m.mark, c.model, c.price FROM cars as c " +
                    "INNER JOIN marks as m ON m.id = c.mark_id WHERE c.id = ? ");

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String mark = rs.getString(1);
                String model = rs.getString(2);
                int price = rs.getInt(3);

                Car car = new Car();

                car.setId(id);
                car.setModel(model);
                car.setMark(mark);
                car.setPrice(price);

//                Car car = new Car(
//                        id,
//                        rs.getString("mark"),
//                        rs.getString("model"),
//                        rs.getInt("price")
//                );


                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, preparedStatement);
        }

        return null;
    }

    @Override
    public void updatePrice(int price, int carId) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE cars SET price = ? WHERE id = ?");

            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, carId);

            int updatedValues = preparedStatement.executeUpdate();

            System.out.println("Values updated: " + updatedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, preparedStatement);
        }

    }

    @Override
    public void removeMark(String mark) {
        Connection connection = Connector.getConnection();;
        PreparedStatement preparedStatement = null;

        try {

            int markId = getMarkId(mark, connection);
            preparedStatement = connection.prepareStatement("DELETE FROM cars WHERE mark_id = ? ");

            preparedStatement.setInt(1, markId);

            int deletedValues = preparedStatement.executeUpdate();

            System.out.println("Values deleted: " + deletedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, preparedStatement);
        }
    }

    @Override
    public void removeModel(String model) {
        Connection connection = Connector.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("DELETE FROM cars WHERE model = ? ");

            preparedStatement.setString(1, model);

            int deletedValues = preparedStatement.executeUpdate();

            System.out.println("Values deleted: " + deletedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, preparedStatement);
        }
    }

    private void closeConnections(Connection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
