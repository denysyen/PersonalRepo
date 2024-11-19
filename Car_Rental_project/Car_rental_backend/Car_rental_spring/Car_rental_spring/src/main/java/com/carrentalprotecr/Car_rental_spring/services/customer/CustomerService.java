package com.carrentalprotecr.Car_rental_spring.services.customer;

import java.util.List;

import com.carrentalprotecr.Car_rental_spring.dto.BookCarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDto;

public interface CustomerService {
    List<CarDto> getAllCars();
    boolean bookCar(BookCarDto bookCarDto);
    CarDto getCarDto(Long carId);

}
