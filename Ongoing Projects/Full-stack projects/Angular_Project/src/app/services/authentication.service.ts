import { HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Constants } from '../constant';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor() { }

  authenticate(username, password): Observable<any> {
    const body =  new HttpParams()
    .set('username', username)
    .set('password', password)
    return this.http.post(Constants.SERVER_URL + 'login', body, {'responseType': 'text'})
    .pipe(map(response => {
      localStorage.setItem('user', btoa(username + ':'+ password));
    }),
    catchError((err: any | HttpErrorResponse) => {
      return throwError(err);
    })
    )
  }
}
