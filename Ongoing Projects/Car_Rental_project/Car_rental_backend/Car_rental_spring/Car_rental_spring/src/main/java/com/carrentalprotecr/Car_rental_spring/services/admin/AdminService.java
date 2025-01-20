package com.carrentalprotecr.Car_rental_spring.services.admin;

import java.io.IOException;
import java.util.List;

import com.carrentalprotecr.Car_rental_spring.dto.BookCarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDtoListDto;
import com.carrentalprotecr.Car_rental_spring.dto.SearchCarDto;

public interface AdminService {
    boolean postCar(CarDto carDto) throws IOException;
    List<CarDto> getAllCars();
    void deleteCar(Long id);
    CarDto getCarById(Long id);
    boolean updateCar(Long carId, CarDto carDto);
    List<BookCarDto> getBookings();
    boolean changeBookingStatus(Long bookingId, String status);
    CarDtoListDto searchCar(SearchCarDto searchCarDto);

}
