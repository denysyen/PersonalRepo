import { Component } from '@angular/core';
import { CcustomerService } from '../../services/ccustomer.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-book-car',
  standalone: true,
  imports: [DatePipe, RouterModule],
  templateUrl: './book-car.component.html',
  styleUrl: './book-car.component.css'
})
export class BookCarComponent {

  activedRoute!: ActivatedRoute
  carId: number = this.activedRoute.snapshot.params["id"];
  car: any;
  processedImg: any;
  constructor(private service: CcustomerService) {

  }
  ngOnInit() {
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

}
