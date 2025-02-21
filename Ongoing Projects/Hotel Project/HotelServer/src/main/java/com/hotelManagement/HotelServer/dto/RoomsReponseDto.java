package com.hotelManagement.HotelServer.dto;

import java.util.List;

import lombok.Data;

@Data
public class RoomsReponseDto {

    private List<RoomDto> roomDtoList;

    private Integer totalPages;

    private Integer pageNumber;

}
