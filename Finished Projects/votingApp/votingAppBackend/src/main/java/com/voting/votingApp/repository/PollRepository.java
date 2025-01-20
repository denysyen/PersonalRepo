package com.voting.votingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.voting.votingApp.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

}
