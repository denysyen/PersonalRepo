package com.voting.votingApp.model;

import java.util.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    // commes from JPA: will create separate table using the same id + the field (options) 
    @ElementCollection
    private List<OptionVote> options = new ArrayList<>();
    


}
