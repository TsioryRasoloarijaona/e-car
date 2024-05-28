package com.web3.ecommerceback.repository;

import com.web3.ecommerceback.entities.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    Optional<Car> findCarsByBrandContainsIgnoreCase(String brand);

    Optional<Car> findCarsByModelContainsIgnoreCase( String model);

    List<Car> findCarsByPriceBetween(double min , double max);

    Optional<Car> findCarsByMotorTypeContainsIgnoreCase(String motorType);

    List<Car> findCarsByStatusEquals(boolean status);

    @Modifying
    @Transactional
    @Query("update Car c set c.status = true where c.id = :id")
    void updateCarById(@Param(value = "id") long id);

    @Modifying
    @Transactional
    @Query("update Car c set c.price = :price where c.id = :id")
    void updateCarPriceById(@Param(value = "price") double price , @Param(value = "id") long id);
}
