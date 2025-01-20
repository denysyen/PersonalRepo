package com.voting.votingApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.voting.votingApp.dto.PollDto;
import com.voting.votingApp.model.Poll;
import com.voting.votingApp.request.Vote;
import com.voting.votingApp.service.PollService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin("http://localhost:4200/") // to solve the CORS error 
public class PollController {
    
    private PollService pollService;

    public PollController (PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll (@RequestBody PollDto poll) {
        return pollService.createPoll(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPollById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @Tag(name = "vote", description = "the vote Api")
    @PostMapping("/vote")
    public void vote(@RequestBody Vote vote) {
        pollService.vote(vote.getPollId(), vote.getOptionIndex());
    }

}
