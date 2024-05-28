package com.web3.ecommerceback.controller;

import com.web3.ecommerceback.entities.Car;
import com.web3.ecommerceback.repository.CarRepository;
import com.web3.ecommerceback.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/car")
public class CarController {
    @Autowired
    private CarRepository repository;
    @Autowired
    private CarService service;

    @PostMapping("/save")
    public String save (@RequestBody Car car) {
     return service.save(car);
    }
    @GetMapping("/allCar")
    public List<Car> allCar() {
        return service.findAll();
    }

    @GetMapping("/byId/{id}")
    public Optional<Car> findById(@PathVariable Long id) {
        return repository.findById(id);
    }

    @GetMapping("/byBrand/{brand}")
    public Optional<Car> byBrand(@PathVariable String brand) {
        return repository.findCarsByBrandContainsIgnoreCase(brand);
    }

    @GetMapping("/byModel/{model}")
    public Optional<Car> findCarsByModel(@PathVariable String model){
        return repository.findCarsByModelContainsIgnoreCase(model);
    }

    @GetMapping("/byPrice")
    public List<Car> findCarsByPriceBetween(@RequestParam double min ,@RequestParam double max){
        return repository.findCarsByPriceBetween(min, max);
    };

    @GetMapping("/byMotor/{motorType}")
    public Optional<Car> findCarsByMotorTypeEquals(String motorType){
        return repository.findCarsByMotorTypeContainsIgnoreCase(motorType);
    };

    @GetMapping("/pined")
    public List<Car> findCarsByStatusEquals(){
        return repository.findCarsByStatusEquals(true);
    };

    @GetMapping("/brandList")
    public List<String> brandList(){
        return service.brandList();
    }

    @GetMapping("/motorList")
    public List<String> motorList(){
        return service.motorList();
    }

    @GetMapping("/modelList")
    public List<String> modelList(){
        return service.modelList();
    }

    @GetMapping("/intervalPrice")
    public List<Double> intervalPrice(){
        return service.priceInterval();
    }

    @PutMapping("/pin/{id}")
    public String pin (@PathVariable long id){
        return service.pin(id);
    }

    @PutMapping("/pin")
    public String updatePirce (@RequestParam double price , @RequestParam long id){
        return service.updatePrice(price,id);
    }




}
