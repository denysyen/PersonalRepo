package com.carrentalprotecr.Car_rental_spring.dto;

import java.util.Date;

import com.carrentalprotecr.Car_rental_spring.enums.BookCarStatus;

import lombok.Data;
@Data
public class BookCarDto {

    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookCarStatus bookCarStatus;
    private Long carId;
    private Long userId;

}
