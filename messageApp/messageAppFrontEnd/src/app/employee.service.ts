import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';
//  The decorator for the Service class
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  
  private baseUrl = "http://localhost:8080/api/v1/employees";
  constructor(private httpClient: HttpClient) { }
  /**
   * To obtin the data from the BE (List of Employeee)
   *  http method get returns an Observable Object from 
   *  `${URL}` input
   * Also get<Observable type response>
   * 
   * @returns Array Of Employee[]
   */
  getEmployeesListt(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${this.baseUrl}`);
  }
}
