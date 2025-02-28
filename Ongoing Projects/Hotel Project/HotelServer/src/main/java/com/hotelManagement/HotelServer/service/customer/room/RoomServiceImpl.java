package com.hotelManagement.HotelServer.service.customer.room;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hotelManagement.HotelServer.dto.RoomsReponseDto;
import com.hotelManagement.HotelServer.entity.Room;
import com.hotelManagement.HotelServer.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomsReponseDto getAvailableRooms(int pageNumber) {
      Pageable pageable =  PageRequest.of(pageNumber, 2);
      Page<Room> roomPage = roomRepository.findByAvailable(true, pageable);
      
      RoomsReponseDto roomsReponseDto = new RoomsReponseDto();
      roomsReponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
      roomsReponseDto.setTotalPages(roomPage.getTotalPages());
      roomsReponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

      return roomsReponseDto;

   }
}
