import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // current authenticated user will be stored in 'localStorage'
    let currentUser = localStorage.getItem('user');

    if (currentUser) {
      request = request.clone({
        setHeaders: {
          Authorization: `Basic ${currentUser}`
        }
      });
    }
    return next.handle(request);

  }
}
