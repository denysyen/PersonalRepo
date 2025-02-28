package com.hotelManagement.HotelServer.service.admin.rooms;

import com.hotelManagement.HotelServer.dto.RoomDto;
import com.hotelManagement.HotelServer.dto.RoomsReponseDto;

public interface RoomsService {

    boolean postRoom(RoomDto roomDto);
    RoomsReponseDto getAllReponse(int pageNumber);
    public RoomDto getRoomById(long id);
    public boolean updateRoom(Long id, RoomDto roomDto);
    public void deleteRoom(Long id);

}
