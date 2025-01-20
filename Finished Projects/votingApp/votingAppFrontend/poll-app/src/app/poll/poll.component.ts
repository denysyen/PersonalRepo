import { Component, OnInit } from '@angular/core';
import { PollService } from '../poll.service';
import { Poll } from '../poll.models';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-poll',
  imports: [CommonModule, FormsModule],
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css'
})
export class PollComponent  implements OnInit{
  newPoll: Poll = {
    id: 0,
    question: '',
    options: [
      {optionText: '', voteCount: 0},
      {optionText: '', voteCount: 0}
    ]
  }
  polls: Poll[] =[];
  constructor(private pollService: PollService){}
  
  // Using the implement interface OnInit to call the method
  // each time that the app is loaded
  ngOnInit(): void {
    this.loadAllPolls();
  }

  loadAllPolls() {
    // subscribe an async call to an END-POINT
    // you need to handle success and error escenarios 
     this.pollService.getPolls().subscribe({
        next: (data) => {
          this.polls = data;
        },
        error: (error) => {
          console.error("Error fetching polls:", error);
        }
     })
  }

  addOption() {
    this.newPoll.options.push({optionText: '', voteCount: 0});
  }

  createPoll() {
   this.pollService.createPoll(this.newPoll).subscribe({
    next: (createdPoll) => {
      this.polls.push(createdPoll);
      this.resetPoll();
    }, 
    error: (error) => {
      console.error("Error while creating a new Poll", error);
    }
   })
  }

  resetPoll() {
    this.newPoll = {
      id: 0,
      question: '',
      options: [
        {optionText: '', voteCount: 0},
        {optionText: '', voteCount: 0}
      ]
    }
  }
  
  vote(pollId: number, optionIndex: number) {
    this.pollService.vote(pollId, optionIndex).subscribe({
      next: () => {
        const poll = this.polls.find(p => p.id === pollId);
        if(poll) {
          poll.options[optionIndex].voteCount++;
        }

      }, 
      error: (error) => {
        console.error("Error while voting" , error);
      } 
    })
  }

  trackByIndex(index: number): number {
    return index;
  }

}
