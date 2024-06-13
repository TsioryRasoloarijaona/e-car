package com.web3.ecommerceback.repository;

import com.web3.ecommerceback.entities.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findCarsByBrandContainsIgnoreCase(String brand);

    List<Car> findCarsByModelContainsIgnoreCase( String model);

    List<Car> findCarsByPriceBetween(double min , double max);

    List<Car> findCarsByMotorTypeContainsIgnoreCase(String motorType);

    List<Car> findCarsByStatusEquals(boolean status);

    List<Car> findCarsByColorContainsIgnoreCase(String color);

    List<Car> findCarsByDescriptionContainsIgnoreCase(String description);

    List<Car> findCarsByNameContainsIgnoreCase(String name);

    List<Car> findCarsByTypeAndMotorTypeIgnoreCaseAndPriceBetween( String type, String motorType, double priceMin, double priceMax);

    @Query("select a from Car a where a.status = true order by a.id desc limit 6")
    List<Car> findCarsOrderByIdDesc();

    @Query("select a from Car a where a.status = true order by a.id asc limit 4")
    List<Car> findCarsShow();


    @Modifying
    @Transactional
    @Query("update Car c set c.status = :status where c.id = :id")
    void updateCarById(@Param(value = "id") long id , @Param(value = "status") boolean status);

    @Modifying
    @Transactional
    @Query("update Car c set c.price = :price where c.id = :id")
    void updateCarPriceById(@Param(value = "price") double price , @Param(value = "id") long id);
}
