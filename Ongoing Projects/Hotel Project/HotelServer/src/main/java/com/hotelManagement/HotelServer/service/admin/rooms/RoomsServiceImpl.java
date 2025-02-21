package com.hotelManagement.HotelServer.service.admin.rooms;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hotelManagement.HotelServer.dto.RoomDto;
import com.hotelManagement.HotelServer.dto.RoomsReponseDto;
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

   public RoomsReponseDto getAllReponse(int pageNumber) {
     Pageable pageable =  PageRequest.of(pageNumber, 6);
     Page<Room> roomPage = roomRepository.findAll(pageable);
    
     RoomsReponseDto roomsReponseDto = new RoomsReponseDto();
     roomsReponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
     roomsReponseDto.setTotalPages(roomPage.getTotalPages());
     roomsReponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

     return roomsReponseDto;

   }
}
