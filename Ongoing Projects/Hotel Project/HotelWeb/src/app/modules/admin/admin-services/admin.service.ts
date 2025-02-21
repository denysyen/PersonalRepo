import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserStotageService } from '../../../auth/service/storage/user-stotage.service';

const BASIC_URL = "http://localhost:8080/";
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  postRoomDetails(roomDto:any): Observable<any>{
    return this.http.post(BASIC_URL + "api/admin/room", roomDto, {
      headers:  this.createAuthorizationHeader(),
    })
  }

  createAuthorizationHeader() {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization',
      'Bearer ' +  UserStotageService.getToken()
    )
  }
}
