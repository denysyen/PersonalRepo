import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Poll } from './poll.models';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PollService {
  private baserUrl = 'http://localhost:8080/api/polls'

  constructor(private http: HttpClient) { }

  createPoll (poll: Poll): Observable<Poll> {
    // sending a post request to the url to create a Poll object
    // here <> function as the function paramater
    return this.http.post<Poll>(this.baserUrl, poll);
  }
  // on this way we make sure to retrieve an array of object
  // by passing as parameter <Poll[]>, Observables are streams of data
  getPolls(): Observable<Poll[]> {
    return this.http.get<Poll[]>(this.baserUrl);
  }

  vote(pollId: number, optionIndex: number): Observable<void> {
    const url = `${this.baserUrl}/vote`;
    // Here we actually need to use return to post new object to BE
    // but we expect nothing in "return" so the parameter to be returned
    //  is a void 
    return this.http.post<void>(url, { pollId, optionIndex });
  }
}
