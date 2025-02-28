package com.hotelManagement.HotelServer.service.customer.room;

import com.hotelManagement.HotelServer.dto.RoomsReponseDto;

public interface RoomService {

    public RoomsReponseDto getAvailableRooms(int pageNumber);

}
