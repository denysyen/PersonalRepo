package com.voting.votingApp.mapper;

import org.springframework.stereotype.Component;

import com.voting.votingApp.dto.OptionVoteDto;
import com.voting.votingApp.model.OptionVote;

@Component
public class OptionsMapper {
    public OptionVote fromDto(OptionVoteDto optionVoteDto) {
        OptionVote optionVote = new OptionVote();

        optionVote.setOptionText(optionVoteDto.getOptionText());
        optionVote.setVoteCount(optionVoteDto.getVoteCount());

        return optionVote;
    }

}
