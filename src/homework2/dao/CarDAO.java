package homework2.dao;


import homework2.entity.Car;

import java.util.List;


public interface CarDAO {

    void add(Car car);

    List<Car> getAll();

    Car getById(int id);

    void updatePrice(int price, int carId);

    void removeMark(String mark);

    void removeModel(String model);

}