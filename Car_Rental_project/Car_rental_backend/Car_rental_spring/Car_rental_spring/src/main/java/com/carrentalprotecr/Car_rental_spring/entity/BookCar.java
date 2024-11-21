package com.carrentalprotecr.Car_rental_spring.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.carrentalprotecr.Car_rental_spring.dto.BookCarDto;
import com.carrentalprotecr.Car_rental_spring.enums.BookCarStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BookCar {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookCarStatus bookCarStatus;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action =OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="car_id", nullable = false)
    @OnDelete(action =OnDeleteAction.CASCADE)
    private Car car;

    public BookCarDto getBookCarDto() {
        BookCarDto bookCarDto =  new BookCarDto();
        bookCarDto.setCarId(id);
        bookCarDto.setDays(days);
        bookCarDto.setBookCarStatus(bookCarStatus);
        bookCarDto.setPrice(price);
        bookCarDto.setToDate(toDate);
        bookCarDto.setFromDate(fromDate);
        bookCarDto.setEmail(user.getEmail());
        bookCarDto.setUsername(user.getName());
        bookCarDto.setCarId(car.getId());

        return bookCarDto;
    }


}
