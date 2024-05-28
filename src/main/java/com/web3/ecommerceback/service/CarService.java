package com.web3.ecommerceback.service;

import com.web3.ecommerceback.entities.Car;
import com.web3.ecommerceback.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
