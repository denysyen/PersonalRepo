package com.carrentalprotecr.Car_rental_spring.services.customer;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.carrentalprotecr.Car_rental_spring.dto.BookCarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDto;
import com.carrentalprotecr.Car_rental_spring.dto.CarDtoListDto;
import com.carrentalprotecr.Car_rental_spring.dto.SearchCarDto;
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

    @Override
    public CarDto getCarDto(Long carId) {
       Optional<Car> optionalCar = carRepository.findById(carId);
       return optionalCar.map(Car::getCarDto).orElse(null);
    }

    @Override
    public List<BookCarDto> getBookingsByUserId(Long userId) {
        return bookCarRepository.findAllByUserId(userId).stream()
            .map(BookCar::getBookCarDto)
            .collect(Collectors.toList());
    }


    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
                Car car = new Car();
        car.setBrand(searchCarDto.getBrand());
        car.setType(searchCarDto.getType());
        car.setTransmission(searchCarDto.getTransmission());
        car.setColor(searchCarDto.getColor());

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
            .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
            
        Example<Car> carExample = Example.of(car, exampleMatcher);
        List<Car> carList = carRepository.findAll(carExample);
        CarDtoListDto carDtoListDto =  new CarDtoListDto();
        carDtoListDto.setCarDtoList(carList.stream().map(Car::getCarDto).collect(Collectors.toList()));

        return carDtoListDto;
    }
}
