package com.voting.votingApp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.voting.votingApp.dto.PollDto;
import com.voting.votingApp.model.OptionVote;
import com.voting.votingApp.model.Poll;

@Component
public class PollMapper {
    
    private OptionsMapper optionsMapper;
   
    public PollMapper(OptionsMapper optionsMapper) {
        this.optionsMapper = optionsMapper;
    }

    public Poll fromDto(PollDto pollDto) {
        Poll poll =  new Poll();
        poll.setQuestion(pollDto.getQuestion());
        List<OptionVote> optionVotes = pollDto.getOptions().stream()
            .map(optionVote -> this.optionsMapper
            .fromDto(optionVote))
            .collect(Collectors.toList());

        poll.setOptions(optionVotes);
        return poll;
    }
}
