package com.carrentalprotecr.Car_rental_spring.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.carrentalprotecr.Car_rental_spring.dto.BookCarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDto;
import com.carrentalprotecr.Car_rental_spring.entity.BookCar;
import com.carrentalprotecr.Car_rental_spring.entity.Car;
import com.carrentalprotecr.Car_rental_spring.entity.User;
import com.carrentalprotecr.Car_rental_spring.enums.BookCarStatus;
import com.carrentalprotecr.Car_rental_spring.repository.BookCarRepository;
import com.carrentalprotecr.Car_rental_spring.repository.CarRepository;
import com.carrentalprotecr.Car_rental_spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookCarRepository bookCarRepository;

    @Override
    public List<CarDto> getAllCars() {
        return carRepository.findAll().stream()
        .map(Car::getCarDto)
        .collect(Collectors.toList());
    }

    @Override
    public boolean bookCar(BookCarDto bookCarDto) {
        Optional<Car> optionalCar = carRepository.findById(bookCarDto.getId());
        Optional<User> optionalUser =  userRepository.findById(bookCarDto.getUserId());

        if(optionalCar.isPresent() && optionalUser.isPresent()) {
            BookCar bookCar = new BookCar();
            bookCar.setUser(optionalUser.get());
            bookCar.setCar(optionalCar.get());
            bookCar.setBookCarStatus(BookCarStatus.PENDING);

            long diffInMilliSeconds = bookCarDto.getToDate().getTime() - bookCarDto.getFromDate().getTime();
            long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
            bookCar.setDays(days);
            bookCar.setPrice(optionalCar.get().getPrice() + days);
            bookCarRepository.save(bookCar);
            
            return true;
        }
        return false;
    }
}
