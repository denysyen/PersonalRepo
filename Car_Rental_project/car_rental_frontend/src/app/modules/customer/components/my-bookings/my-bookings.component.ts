import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CcustomerService } from '../../services/ccustomer.service';
import { CommonModule, DatePipe } from '@angular/common';
import { NgZorroImportsModule } from '../../../../NgZorroImportsModule';
import { ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-my-bookings',
  standalone: true,
  imports: [DatePipe, NgZorroImportsModule, ReactiveFormsModule , CommonModule],
  templateUrl: './my-bookings.component.html',
  styleUrl: './my-bookings.component.css',
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class MyBookingsComponent {
  
  bookings: any;
  isSpinning = false;
  constructor(private service: CcustomerService) {
    this.getMyBookings();
  }

  getMyBookings() {
    this.isSpinning = true;
    this.service.getBookingsByUserId().subscribe((res) => {
       console.log(res); 
       this.bookings = res; 
       this.isSpinning = false;
    })
  }

}
