package com.hotelManagement.HotelServer.service.customer.booking;

import com.hotelManagement.HotelServer.dto.ReservationDto;

public interface BookingService {
public boolean postReservation(ReservationDto reservationDto);
}
