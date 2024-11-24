package com.carrentalprotecr.Car_rental_spring.services.customer;

import java.util.List;

import com.carrentalprotecr.Car_rental_spring.dto.BookCarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDtoListDto;
import com.carrentalprotecr.Car_rental_spring.dto.SearchCarDto;

public interface CustomerService {
    List<CarDto> getAllCars();
    boolean bookCar(BookCarDto bookCarDto);
    CarDto getCarDto(Long carId);
    List<BookCarDto> getBookingsByUserId(Long userId);
    
    CarDtoListDto searchCar(SearchCarDto searchCarDto);

}
