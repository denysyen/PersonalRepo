package com.carrentalprotecr.Car_rental_spring.services.admin;

import java.io.IOException;
import java.util.List;

import com.carrentalprotecr.Car_rental_spring.dto.CarDto;

public interface AdminService {
    boolean postCar(CarDto carDto) throws IOException;
    List<CarDto> getAllCars();
    void deleteCar(Long id);

}
