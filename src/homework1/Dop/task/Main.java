package homework1.Dop.task;


import java.sql.*;


public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/testbase";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "12345678";

    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();


            String testTable = "CREATE TABLE testtable " +
                    "(id INT auto_increment primary key , " +
                    " name VARCHAR(50) not null, " +
                    " phone VARCHAR (50) not null, " +
                    " salary int not NULL " +
                    " )";

            statement.executeUpdate(testTable);

            preparedStatement = connection.prepareStatement("INSERT INTO testtable(id, name, phone, salary) VALUES (?, ?, ?, ?)");

            for (int i = 1; i < 10; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "Name" + i);
                preparedStatement.setString(3, "(495)547221" + i);
                preparedStatement.setInt(4, 1000 * i);
                preparedStatement.execute();
            }

            ResultSet resultSet = statement.executeQuery("SELECT * FROM testtable where id=2;");
            resultSet.next();
            select(resultSet);

            statement.executeUpdate("UPDATE testtable SET name = 'name22', phone= '(666)5472999', salary = 666666  WHERE id=2;");
            resultSet = statement.executeQuery("SELECT * FROM testtable where id=2;");
            resultSet.next();
            select(resultSet);

            statement.executeUpdate("Delete from testtable  WHERE id=2;");
            resultSet = statement.executeQuery("SELECT * FROM testtable where id=2;");
            resultSet.next();

            if (!resultSet.next()) {
                System.out.println("no data");
            } else {
                select(resultSet);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void select(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String name = resultSet.getString(2);
        String phone = resultSet.getString(3);
        int salary = resultSet.getInt(4);
        System.out.println(id + " " + name + " " + phone + " " + salary);

    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loading success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
