package homework2.dao;

import homework2.entity.Client;

import java.util.List;

public interface ClientDAO {

    void addClient(Client client);

    Client getClient(int id);

    List<Client> getAllClients();

    void updateClient(long id, String name, int age, String phone);

    void removeClient(long id);


}
