package com.carrentalprotecr.Car_rental_spring.entity;



import java.util.Date;

import com.carrentalprotecr.Car_rental_spring.dto.CarDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name ="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String color;
    private String name;
    private String type;
    private String transmission;
    private String description;
    private Long price;
    private Date year;
    @Column(columnDefinition = "longblob")
    private byte[] image;

    public CarDto getCarDto() {
        CarDto carDto  = new CarDto();
        carDto.setId(id);
        carDto.setName(name);
        carDto.setBrand(brand);
        carDto.setColor(color);
        carDto.setDescription(description);
        carDto.setPrice(price);
        carDto.setTransmission(transmission);
        carDto.setYear(year);
        carDto.setType(type);
        carDto.setReturnedImage(image);
        return carDto;
    };




}
