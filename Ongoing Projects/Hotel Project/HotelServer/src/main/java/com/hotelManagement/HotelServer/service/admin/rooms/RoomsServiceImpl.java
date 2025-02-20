package com.hotelManagement.HotelServer.service.admin.rooms;

import org.springframework.stereotype.Service;

import com.hotelManagement.HotelServer.dto.RoomDto;
import com.hotelManagement.HotelServer.entity.Room;
import com.hotelManagement.HotelServer.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {
   
   private final RoomRepository roomRepository;

   public boolean postRoom(RoomDto roomDto) {
    try{
       
        Room room = new Room();
        room.setName(roomDto.getName());
        room.setPrice(roomDto.getPrice());
        room.setType(roomDto.getType());
        room.setAvailable(true);

        roomRepository.save(room);
        return true;
    } catch (Exception e) {
        return false;
    }
   }
}
