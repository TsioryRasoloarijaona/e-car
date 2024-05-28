package com.web3.ecommerceback.service;

import com.web3.ecommerceback.entities.Car;
import com.web3.ecommerceback.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public List<Car> findAll() {
        return repository.findAll();
    }

    public String save(Car car) {
        try {
            repository.save(car);
            return "car saved";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }

    }

    public List<String> brandList(){
        List<Car> cars = repository.findAll();
        return cars.stream().map(Car::getBrand).collect(Collectors.toList());
    }

    public List<String> modelList(){
        List<Car> cars = repository.findAll();
        return cars.stream().map(Car::getModel).collect(Collectors.toList());
    }

    public List<String> motorList(){
        List<Car> cars = repository.findAll();
        return cars.stream().map(Car::getMotorType).collect(Collectors.toList());
    }

    public List<Double> priceInterval(){
        List<Car> cars = repository.findAll();
        double max = cars.stream().map(Car::getPrice).max(Double::compareTo).get();
        double min = cars.stream().map(Car::getPrice).min(Double::compareTo).get();

        return List.of(min , max);
    }
}
