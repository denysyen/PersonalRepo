package com.carrentalprotecr.Car_rental_spring.services.admin;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.carrentalprotecr.Car_rental_spring.dto.BookCarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDto;
import com.carrentalprotecr.Car_rental_spring.entity.BookCar;
import com.carrentalprotecr.Car_rental_spring.entity.Car;
import com.carrentalprotecr.Car_rental_spring.enums.BookCarStatus;
import com.carrentalprotecr.Car_rental_spring.repository.BookCarRepository;
import com.carrentalprotecr.Car_rental_spring.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl  implements AdminService {
    private final CarRepository carRepository;
    private final BookCarRepository bookCarRepository;

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

    @Override
    public CarDto getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(Car::getCarDto).orElse(null);
        
    }

    @Override
    public boolean updateCar(Long carId, CarDto carDto) {
       Optional<Car> optionalCar = carRepository.findById(carId);
       if(optionalCar.isPresent()){
        Car existingCar = optionalCar.get();
        if(carDto.getImage() != null) {
            try {
                existingCar.setImage(carDto.getImage().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            existingCar.setYear(carDto.getYear());
            existingCar.setPrice(carDto.getPrice());
            existingCar.setType(carDto.getType());
            existingCar.setBrand(carDto.getBrand());
            existingCar.setColor(carDto.getColor());
            existingCar.setDescription(carDto.getDescription());
            existingCar.setName(carDto.getName());
            carRepository.save(existingCar);
            return true;
        }
       }
       return false;
    }

    @Override
    public List<BookCarDto> getBookings() {
       return bookCarRepository.findAll().stream()
            .map(BookCar::getBookCarDto)
            .collect(Collectors.toList());
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookCar> optionalCar = bookCarRepository.findById(bookingId);
        if(optionalCar.isPresent()) {
            BookCar existingBookCar = optionalCar.get();
            if(Objects.equals(status, "Approve")) {
                existingBookCar.setBookCarStatus(BookCarStatus.APPROVED);
            } else {
                existingBookCar.setBookCarStatus(BookCarStatus.REJECTED);
            }
            bookCarRepository.save(existingBookCar);
            return true;
        }
        return false; 
    }


}
