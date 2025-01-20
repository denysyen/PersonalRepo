package com.voting.votingApp.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class OptionVoteDto {
    private String optionText;
    private Long voteCount = 0L;

}
