package com.carrentalprotecr.Car_rental_spring.services.admin;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.carrentalprotecr.Car_rental_spring.dto.CarDto;
import com.carrentalprotecr.Car_rental_spring.entity.Car;
import com.carrentalprotecr.Car_rental_spring.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl  implements AdminService {
    private final CarRepository carRepository;

    @Override
    public boolean postCar(CarDto carDto)  {
        Car car = new Car();
        try {
            car.setName(carDto.getName());
            car.setBrand(carDto.getBrand());
            car.setColor(carDto.getColor());
            car.setPrice(carDto.getPrice());
            car.setYear(carDto.getYear());
            car.setImage(carDto.getImage().getBytes());
            car.setYear(carDto.getYear());
            car.setDescription(carDto.getDescription());
            carRepository.save(car);
            return true;
        
        } catch (IOException e) {
             e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CarDto> getAllCars() {
       return carRepository.findAll().stream()
        .map(Car::getCarDto)
        .collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


}
