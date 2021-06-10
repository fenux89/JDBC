package homework1.task4;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "12345678";


    public static void main(String[] args) {
        registerDriver();

        Connection connection = null;
        Statement statement = null;
        List<Employee_data> employee_data = new ArrayList<>();
        List<Unmarried_employees> unmarried_employees = new ArrayList<>();
        List<Managers> managers = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select employees.id, employees.name, phone, place_residence from employees, personal_information where employees.per_inf_id =personal_information.id  ;");


            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String phone = resultSet.getString(3);
                String place_residence = resultSet.getString(4);
                Employee_data employeeData = new Employee_data(id, name, phone, place_residence);
                employee_data.add(employeeData);

                System.out.println(id + " " + " " + name + " " + phone + " " + place_residence);
            }
            System.out.println();


            resultSet = statement.executeQuery("select employees.id, employees.name, Date_Birth, phone,  marital_status from employees, personal_information where employees.id=personal_information.id and personal_information.marital_status='не женат';");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Date Date_Birth = resultSet.getDate(3);
                String phone = resultSet.getString(4);
                String marital_status = resultSet.getString(5);
                Unmarried_employees unmarriedEmployees = new Unmarried_employees(id, name, Date_Birth, phone, marital_status);
                unmarried_employees.add(unmarriedEmployees);

                System.out.println(id + " " + " " + name + " " + Date_Birth + " " + phone + " " + marital_status);
            }

            System.out.println();

            resultSet = statement.executeQuery("select employees.id, employees.name, post.post, Date_Birth, phone from employees, personal_information, post where employees.id = personal_information.id and employees.post_id=post.id and post='менеджер';");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String post = resultSet.getString(3);
                Date Date_Birth = resultSet.getDate(4);
                String phone = resultSet.getString(5);
                Managers manager = new Managers(id, name, post, Date_Birth, phone);
                managers.add(manager);

                System.out.println(id + " " + " " + name + " " + post + " " + Date_Birth + " " + phone);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
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
