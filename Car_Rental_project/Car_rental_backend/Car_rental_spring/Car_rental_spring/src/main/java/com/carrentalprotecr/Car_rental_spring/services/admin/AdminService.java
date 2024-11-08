package com.carrentalprotecr.Car_rental_spring.services.admin;

import java.io.IOException;

import com.carrentalprotecr.Car_rental_spring.dto.CarDto;

public interface AdminService {
    boolean postCar(CarDto carDto) throws IOException;

}
