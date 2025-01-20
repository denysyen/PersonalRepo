package com.carrentalprotecr.Car_rental_spring.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CarDto {
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
    private MultipartFile image;
    private  byte[] returnedImage;

}
