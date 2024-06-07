package com.web3.ecommerceback.controller;

import com.web3.ecommerceback.entities.Car;
import com.web3.ecommerceback.entities.Message;
import com.web3.ecommerceback.repository.CarRepository;
import com.web3.ecommerceback.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/car")
@AllArgsConstructor
public class CarController {
    private CarRepository repository;
    private CarService service;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/save")
    public Message save (@RequestBody Car car) {
     return service.save(car);
    }

    @PostMapping("/save/many")
    public String saveMany (@RequestBody List<Car> cars) {
        return service.saveMany(cars);
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
    public List<Car> byBrand(@PathVariable String brand) {
        return repository.findCarsByBrandContainsIgnoreCase(brand);
    }

    @GetMapping("/byName/{name}")
    public List<Car> byName(@PathVariable String name) {
        return repository.findCarsByNameContainsIgnoreCase(name);
    }

    @GetMapping("/byModel/{model}")
    public List<Car> findCarsByModel(@PathVariable String model){
        return repository.findCarsByModelContainsIgnoreCase(model);
    }

    @GetMapping("/byPrice")
    public List<Car> findCarsByPriceBetween(@RequestParam double min ,@RequestParam double max){
        return repository.findCarsByPriceBetween(min, max);
    };

    @GetMapping("/byMotor/{motorType}")
    public List<Car> findCarsByMotorType(@PathVariable String motorType){
        return repository.findCarsByMotorTypeContainsIgnoreCase(motorType);
    };

    @GetMapping("/motor/type/price")
    public List<Car> filter(@RequestParam String type,@RequestParam String motorType,@RequestParam double priceMin, double priceMax){
        return repository.findCarsByTypeAndMotorTypeIgnoreCaseAndPriceBetween(type,motorType,priceMin,priceMax);
    }

    @GetMapping("/pined")
    public List<Car> findCarsByStatusEquals(){
        return repository.findCarsByStatusEquals(true);
    };

    @GetMapping("/brandList")
    public List<String> brandList(){
        return service.brandList();
    }

    @GetMapping("/colorList")
    public List<String> colorList(){
        return service.colorList();
    }

    @GetMapping("/nameList")
    public List<String> nameList(){
        return service.nameList();
    }

    @GetMapping("/motorList")
    public List<String> motorList(){
        return service.motorList();
    }

    @GetMapping("/modelList")
    public List<String> modelList(){
        return service.modelList();
    }

    @GetMapping("/typeList")
    public List<String> typeList(){
        return service.typeList();
    }

    @GetMapping("/intervalPrice")
    public List<Double[]> intervalPrice(){
        return service.intervals();
    }

    @PutMapping("/statusFalse/{id}")
    public Message pin (@PathVariable long id){
        return service.statusFalse(id);
    }

    @PutMapping("/unPin/{id}")
    public Message unPin (@PathVariable long id){
        return service.unPin(id);
    }



    @PutMapping("/priceUpdate")
    public Message updatePrice (@RequestParam double price , @RequestParam long id){
        return service.updatePrice(price,id);
    }

    @GetMapping("/research")
    public List<Car> research (@RequestParam String input){
        return service.research(input);
    }

    @GetMapping("/latest")
    public List<Car> latest(){
        return repository.findCarsOrderByIdDesc();
    }




}
