package homework2.dao;

public interface IDAOFactory {

    CarDAO getCarDAO();

    ClientDAO getClientDAO();


}
