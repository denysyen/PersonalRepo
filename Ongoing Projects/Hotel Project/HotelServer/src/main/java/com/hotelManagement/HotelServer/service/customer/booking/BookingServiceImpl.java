package com.hotelManagement.HotelServer.service.customer.booking;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hotelManagement.HotelServer.dto.ReservationDto;
import com.hotelManagement.HotelServer.entity.Reservation;
import com.hotelManagement.HotelServer.entity.Room;
import com.hotelManagement.HotelServer.entity.User;
import com.hotelManagement.HotelServer.enums.ReservationStatus;
import com.hotelManagement.HotelServer.repository.ReservationRepository;
import com.hotelManagement.HotelServer.repository.RoomRepository;
import com.hotelManagement.HotelServer.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public boolean postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom  = roomRepository.findById(reservationDto.getRoomId());

        if(optionalRoom.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();

            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservation.getCheckInDate());
            reservation.setCheckOutDate(reservation.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice()* days);

            reservationRepository.save(reservation);
            return true;
        }

        return false;
    }

}
