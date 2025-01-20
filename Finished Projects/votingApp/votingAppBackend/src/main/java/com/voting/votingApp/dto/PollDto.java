package com.voting.votingApp.dto;

import java.util.*;

import lombok.NoArgsConstructor;

import lombok.Data;


@Data
@NoArgsConstructor
public class PollDto {

   private String question;
   private List<OptionVoteDto> options = new ArrayList<>();

}
