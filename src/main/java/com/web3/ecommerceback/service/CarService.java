package com.web3.ecommerceback.service;

import com.web3.ecommerceback.entities.Car;
import com.web3.ecommerceback.entities.Message;
import com.web3.ecommerceback.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CarService {
    @Autowired
    private CarRepository repository;

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Message save(Car car) {
        try {
            car.setStatus(true);
            repository.save(car);
            return new Message( "car saved",null);
        }catch (Exception e){
            e.printStackTrace();
            return new Message(null,"failed to save car");
        }

    }

    public String saveMany(List<Car> cars) {
            for (Car car : cars){
                save(car);
            }
            return "cars saved";
    }

    public List<String> brandList(){
        List<Car> cars = repository.findAll();
        Set<String> uniqueBrands = new HashSet<>();
        cars.stream().map(Car::getBrand).forEach(uniqueBrands::add);
        return new ArrayList<>(uniqueBrands);
    }

    public List<String> modelList(){
        List<Car> cars = repository.findAll();
        Set<String> uniqueModel = new HashSet<>();
         cars.stream().map(Car::getModel).forEach(uniqueModel::add);
         return new ArrayList<>(uniqueModel);
    }

    public List<String> motorList(){
        List<Car> cars = repository.findAll();
        Set<String> uniqueMotors = new HashSet<>();
        cars.stream().map(Car::getMotorType).forEach(uniqueMotors::add);
        return new ArrayList<>(uniqueMotors);
    }

    public List<String> colorList(){
        List<Car> cars = repository.findAll();
        Set<String> uniqueColors = new HashSet<>();
        cars.stream().map(Car::getColor).forEach(uniqueColors::add);
        return new ArrayList<>(uniqueColors);
    }

    public List<String> nameList(){
        List<Car> cars = repository.findAll();
        Set<String> uniqueNames = new HashSet<>();
        cars.stream().map(Car::getName).forEach(uniqueNames::add);
        return new ArrayList<>(uniqueNames);
    }

    public List<String> typeList(){
        List<Car> cars = repository.findAll();
        Set<String> uniqueTypes = new HashSet<>();
        cars.stream().map(Car::getType).forEach(uniqueTypes::add);
        return new ArrayList<>(uniqueTypes);
    }

    private List<Double> priceInterval(){
        List<Car> cars = repository.findAll();
        double max = cars.stream().map(Car::getPrice).max(Double::compareTo).get();
        double min = cars.stream().map(Car::getPrice).min(Double::compareTo).get();

        return List.of(min , max);
    }


    private List<Double[]> generateIntervals(double min, double max) {
        List<Double[]> intervals = new ArrayList<>();

        double range = max - min;
        double intervalSize = range / 6;

        for (int i = 0; i < 6; i++) {
            double start = min + i * intervalSize;
            double end = (i == 6 - 1) ? max : start + intervalSize;
            intervals.add(new Double[]{start, end});
        }

        return intervals;
    }

    public List<Double[]> intervals (){
        return generateIntervals(priceInterval().get(0),priceInterval().get(1));
    }

    public Message statusFalse(long id){
        try {
            repository.updateCarById(id , false);
            return new Message("pinned successfully",null);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Message(null,"failed to update");
        }
    }

    public Message unPin(long id){
        try {
            repository.updateCarById(id , false);
            return new Message("pinned successfully",null);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Message(null,"failed to update");
        }
    }

    public Message updatePrice(double price , long id){
        try {
            repository.updateCarPriceById(price,id);
            return new Message("updated successfully",null);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Message(null,"failed to update");
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
                result.addAll(motor);
            }

            List<Car> color = repository.findCarsByColorContainsIgnoreCase(word);
            if (!color.isEmpty()){
                result.addAll(color);
            }

            List<Car> description = repository.findCarsByDescriptionContainsIgnoreCase(word);
            if (!description.isEmpty()){
                result.addAll(description);
            }

            List<Car> name = repository.findCarsByNameContainsIgnoreCase(word);
            if (!description.isEmpty()){
                result.addAll(name);
            }
        }
        return result.stream().distinct().toList();
    }
}
