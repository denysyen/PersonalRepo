package com.voting.votingApp.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable  //  because this class will be embedded into a another class attribute !
public class OptionVote {
    private String optionText;
    private Long voteCount = 0L;

}
