package com.web3.ecommerceback.repository;

import com.web3.ecommerceback.entities.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageCar extends PagingAndSortingRepository<Car, Long> {

}
