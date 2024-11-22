import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NgZorroImportsModule } from '../../../../NgZorroImportsModule';
import { CommonModule, DatePipe } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-get-bookings',
  standalone: true,
  imports: [NgZorroImportsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './get-bookings.component.html',
  styleUrl: './get-bookings.component.css',
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class GetBookingsComponent {
  isSpinning = false;
  bookings: any;
  constructor(private adminService: AdminService){}

  getBookings(){
    this.isSpinning = true;
    this.adminService.getCarBookings().subscribe((res) => {
      console.log(res);
      this.bookings = res;
      this.isSpinning =  false;
    })
  }
   
}
