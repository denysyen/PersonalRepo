package com.hotelManagement.HotelServer.service.admin.rooms;

import com.hotelManagement.HotelServer.dto.RoomDto;
import com.hotelManagement.HotelServer.dto.RoomsReponseDto;

public interface RoomsService {

    boolean postRoom(RoomDto roomDto);
    RoomsReponseDto getAllReponse(int pageNumber);

}
