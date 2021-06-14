package homework2;

import homework2.dao.CarDAO;
import homework2.dao.ClientDAO;
import homework2.dao.DAOFactory;
import homework2.dao.IDAOFactory;
import homework2.entity.Client;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        CarDAO carDAO = factory.getCarDAO();

        IDAOFactory factory1 = DAOFactory.getInstance();
        ClientDAO clientDAO = factory1.getClientDAO();

        Client client = new Client("alex", 33, "(959)2587413");
        clientDAO.addClient(client);

        System.out.println(clientDAO.getClient(4));

        List<Client> clientList = clientDAO.getAllClients();

        for (Client client1 : clientList) {
            System.out.println(client1);
        }

        clientDAO.updateClient(4, "Fedor", 99, "(999)6664758");

        clientDAO.removeClient(4);


//        Car car = new Car("Tesla", "model X", 100000);
//
//        carDAO.add(car);

//        List<Car> carList = carDAO.getAll();
//
//        for(Car car: carList) {
//            System.out.println(car);
//        }

        //carDAO.updatePrice(1111111, 15);
///*
//        carDAO.removeModel("model X");
//        carDAO.removeMark("Lada");*/
    }

}
