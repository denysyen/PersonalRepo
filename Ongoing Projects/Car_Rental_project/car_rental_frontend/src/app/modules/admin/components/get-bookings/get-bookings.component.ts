import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NgZorroImportsModule } from '../../../../NgZorroImportsModule';
import { CommonModule, DatePipe } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { error } from 'console';

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
  constructor(private adminService: AdminService, private message: NzMessageService){}

  getBookings(){
    this.isSpinning = true;
    this.adminService.getCarBookings().subscribe((res) => {
      console.log(res);
      this.bookings = res;
      this.isSpinning =  false;
    })
  }
  changeBookingStatus(bookingId: number, status: string) {
     console.log(status);
     this.isSpinning = true;
     this.adminService.changeBookingsStatus(bookingId, status).subscribe((res) => {
         console.log(res);
         this.isSpinning = false;
         this.getBookings();
         this.message.success("Booking status changed successfully", {nzDuration: 3000});
     }, error => {
      this.message.error("Something went wrong ", {nzDuration: 3000});
     })

  }
   
}
