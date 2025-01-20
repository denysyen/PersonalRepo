import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { NgZorroImportsModule } from './NgZorroImportsModule';
import { StorageService } from './auth/services/storage/storage.service';
import { CommonModule } from '@angular/common';  
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgZorroImportsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'car_rental_ui';
  
  isCustomerLoggedIn: boolean = StorageService.isCustomerLoggedIn();
  isAdminLoggedIn: boolean = StorageService.isAdminLoggedIn();

  constructor(private router: Router) {}
  
  ngOnInit() {
    this.router.events.subscribe(event => {
      if(event.constructor.name == "NavigationEnd") {
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isCustomerLoggedIn();
      }      
    })
  }

  logout() {
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }
}

