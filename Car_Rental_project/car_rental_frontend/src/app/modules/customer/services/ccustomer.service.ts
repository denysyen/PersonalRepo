import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = "http://localhost:8080";
@Injectable({
  providedIn: 'root'
})
export class CcustomerService {

  constructor(private http: HttpClient) { }

  getAllCars(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/customer/cars", {
        headers: this.createAutherizationHeader()
    });
  }

  getCarById(cardId: number): Observable<any> {
    return this.http.get(BASIC_URL + "/api/customer/car/" + cardId, {
      headers: this.createAutherizationHeader()
    });
  }

 getBookingsByUserId(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/customer/car/bookings" + StorageService.getUserId(), {
      headers: this.createAutherizationHeader()
    });
  }

  bookCar(bookCarDto: any): Observable<any> {
    return this.http.post(BASIC_URL + "/api/customer/car/book" + bookCarDto, {
      headers: this.createAutherizationHeader()
    });
  } 

  createAutherizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders
    .set('Authorization','Bearer' + StorageService.getToken());
  }
}
