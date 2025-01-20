package com.voting.votingApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.voting.votingApp.dto.PollDto;
import com.voting.votingApp.mapper.PollMapper;
import com.voting.votingApp.model.OptionVote;
import com.voting.votingApp.model.Poll;
import com.voting.votingApp.repository.PollRepository;

@Service
public class PollService {
    private PollRepository pollRepository;
    private PollMapper pollMapper;

    public PollService(PollRepository pollRepository, PollMapper pollMapper) {
        this.pollRepository = pollRepository;
        this.pollMapper = pollMapper;
    } 

    public Poll createPoll(PollDto poll) {
        Poll mapperPoll = pollMapper.fromDto(poll);
        return pollRepository.save(mapperPoll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById (Long id) {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex) {
        Poll poll =  pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException(" Poll not found"));
        
        List<OptionVote> options = poll.getOptions();

        // if index for voted is not valid, throw error 
        if(optionIndex < 0 || optionIndex >= options.size()) {
            throw new IllegalArgumentException("Invalid option index");
        }

        // retrive selected option 
        OptionVote selectedOption = options.get(optionIndex);
        // increment vote for the selected option 
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

        pollRepository.save(poll);

    }

}
