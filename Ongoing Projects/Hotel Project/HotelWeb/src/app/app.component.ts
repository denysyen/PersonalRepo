import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterOutlet, RouterModule  } from '@angular/router';
import { NgZorroAntModule } from './NgZorroAntModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserStotageService } from './auth/service/storage/user-stotage.service';
import { Router } from '@angular/router';

const BASIC_URL = "http://localhost:4200";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NgZorroAntModule, 
    ReactiveFormsModule, FormsModule, RouterModule  
    //  RouterModule is need in order to ensure proper navigation on click events 
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA ]
})

export class AppComponent {
  title = 'HotelWeb';

  isCustomerLoggedIn: boolean = UserStotageService.isCustomerLoggedIn();
  isAdminLoggedIn: boolean = UserStotageService.isAdminLoggedIn();

  constructor(private router: Router) {}

  navigateTo(url: string){
    this.router.navigateByUrl(BASIC_URL + url);
  }

  ngOnInit(){
    this.router.events.subscribe(event => {
      if(event.constructor.name == "NavigationEnd"){
        this.isCustomerLoggedIn = UserStotageService.isCustomerLoggedIn();
        this.isAdminLoggedIn = UserStotageService.isAdminLoggedIn();
      }
    })
  }
  logout() {
    UserStotageService.signOut();
    this.router.navigateByUrl('/');
  }
}
