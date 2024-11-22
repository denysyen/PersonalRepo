import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth/services/storage/storage.service';

const BASIC_URL = "http://localhost:8080";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  postCar(carDto: any):Observable<any> {
    return this.http.post(BASIC_URL + "/api/admin/car", carDto, {
      headers: this.createAutherizationHeader()
    });
  }

  getAllCars(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/cars", {
        headers: this.createAutherizationHeader()
    });
  }

  createAutherizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    // TODO --> search for a method to produce always a 
    // signature validated JWT token for UFT-8 format
    return authHeaders
    .set('Authorization','Bearer' + StorageService.getToken());
     
  }

  getCarById(id: number): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/car" + id, {
      headers: this.createAutherizationHeader()
    });
  }

  updateCar(carId: number, carDto: any) {
    return this.http.put(BASIC_URL + "/api/admin/car/" + carId, carDto, {
      headers: this.createAutherizationHeader()
    })
  }

  deleteCar(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + "/api/car/" + id, {
      headers: this.createAutherizationHeader()
    });
  }

  getCarBookings(): Observable<any> {
    return this.http.get(BASIC_URL + "/api/admin/car/bookings", {
        headers: this.createAutherizationHeader()
    });
  }
}
