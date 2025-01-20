import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CcustomerService } from '../../services/ccustomer.service';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { DatePipe, formatDate } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { StorageService } from '../../../../auth/services/storage/storage.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NgZorroImportsModule } from '../../../../NgZorroImportsModule';

@Component({
  selector: 'app-book-car',
  standalone: true,
  imports: [DatePipe, RouterModule, NgZorroImportsModule, ReactiveFormsModule],
  templateUrl: './book-car.component.html',
  styleUrl: './book-car.component.css'
})
export class BookCarComponent {

  activedRoute!: ActivatedRoute
  carId: number = this.activedRoute.snapshot.params["id"];
  car: any;
  processedImg: any;
  validateForm!: FormGroup;
  isSpinning = false;
  dateFormat!: "DD-MM-YYYY";
  constructor(private service: CcustomerService, private fb: FormBuilder, 
    private message: NzMessageService, private router: Router) {

  }
  ngOnInit() {
    this.validateForm = this.fb.group({
      toDate:[null, Validators.required],
      fromDate: [null, Validators.required]
    })
   this.getCarById();
  }

  getCarById() {
    this.service.getCarById(this.carId).subscribe({
      next: (res) => {
        console.log(res);
        this.processedImg = 'data:image/jpeg;base64,' + res.returnedImage;
        this.car = res;
      }, 
      error: (error) => {
        console.log(error);
      }
    });
  }

  bookCar(data: any) {
     console.log(data);
     this.isSpinning = true;
     let bookCarDto = {
      toDate: data.toDate,
      fromDate: data.fromDate,
      userId: StorageService.getUserId(),
      carId: this.carId
    }
    this.service.bookCar(bookCarDto).subscribe((res) => {
      console.log(res);
      this.message.success("Booking request submitted successfully", {nzDuration: 3000});
      this.router.navigateByUrl("/customer/dashboard");
    }, error => {
      this.message.error("Something went wrong", {nzDuration: 3000});
    })
  }

}
