package com.web3.ecommerceback.repository;

import com.web3.ecommerceback.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByBrand(String brand);
    List<Car> findCarsByModel( String model);
    List<Car> findCarsByPriceBetween(double min , double max);
    List<Car> findCarsByMotorTypeEquals(String motorType);
    List<Car> findCarsByStatusEquals(boolean status);

}
