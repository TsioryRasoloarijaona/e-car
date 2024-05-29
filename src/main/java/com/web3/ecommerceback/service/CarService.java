package com.web3.ecommerceback.service;

import com.web3.ecommerceback.entities.Car;
import com.web3.ecommerceback.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String pin(long id){
        try {
            repository.updateCarById(id);
            return "updated successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            return "failed to update";
        }
    }

    public String updatePrice(double price ,long id){
        try {
            repository.updateCarPriceById(price,id);
            return "updated successfully";
        }
        catch (Exception e){
            e.printStackTrace();
            return "failed to update";
        }

    }


    public List<Car> research(String input){

        List<String> keyWord = new ArrayList<>(Arrays.asList(input.split("\\s+")));

        List<Car> result = new ArrayList<>();

        for (String word : keyWord){

            List<Car> brand = repository.findCarsByBrandContainsIgnoreCase(word);
            if (!brand.isEmpty()){
                result.addAll(brand);
            }

            List<Car> model = repository.findCarsByModelContainsIgnoreCase(word);
            if (!model.isEmpty()){
                result.addAll(model);
            }

            List<Car> motor = repository.findCarsByMotorTypeContainsIgnoreCase(word);
            if (!motor.isEmpty()){
                result.addAll(model);
            }

            List<Car> color = repository.findCarsByColorContainsIgnoreCase(word);
            if (!color.isEmpty()){
                result.addAll(color);
            }

            List<Car> description = repository.findCarsByDescriptionContainsIgnoreCase(word);
            if (!description.isEmpty()){
                result.addAll(description);
            }
        }
        return result.stream().distinct().toList();
    }
}
