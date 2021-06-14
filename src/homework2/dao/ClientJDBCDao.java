package homework2.dao;

import homework2.connection.Connector;
import homework2.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientJDBCDao implements ClientDAO {


    @Override
    public void addClient(Client client) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("INSERT INTO clients(name, age, phone) VALUES (?, ?, ?)");

            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setString(3, client.getPhone());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

    }


    @Override
    public Client getClient(int id) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement("SELECT name, age, phone from clients WHERE id=" + id + ";");

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String name = rs.getString(1);
                int age = rs.getInt(2);
                String phone = rs.getString(3);

                Client client = new Client();

                client.setId(id);
                client.setName(name);
                client.setAge(age);
                client.setPhone(phone);

                return client;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

        return null;
    }


    @Override
    public List<Client> getAllClients() {
        List<Client> allClients = new ArrayList<>();
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * from Clients;");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Client client = new Client(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                allClients.add(client);
            }
            return allClients;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }


        return null;
    }

    @Override
    public void updateClient(long id, String name, int age, String phone) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("UPDATE clients SET name = ?, age = ?, phone = ? WHERE id = ?");

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, phone);
            statement.setLong(4, id);

            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
        }

    }

    @Override
    public void removeClient(long id) {
        Connection connection = Connector.getConnection();
        PreparedStatement statement = null;


        try {
            statement = connection.prepareStatement("DELETE FROM clients WHERE id= ? ");

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnections(connection, statement);
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
