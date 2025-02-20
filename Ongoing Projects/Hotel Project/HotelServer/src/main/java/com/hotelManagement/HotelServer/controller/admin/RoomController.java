package com.hotelManagement.HotelServer.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelManagement.HotelServer.dto.RoomDto;
import com.hotelManagement.HotelServer.service.admin.rooms.RoomsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/")
@RequiredArgsConstructor
public class RoomController {

    
    private final RoomsService roomsService;
    
    @PostMapping("/room")
    public ResponseEntity<?> postRoom(@RequestBody RoomDto  roomDto){
        boolean success = roomsService.postRoom(roomDto);
        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
