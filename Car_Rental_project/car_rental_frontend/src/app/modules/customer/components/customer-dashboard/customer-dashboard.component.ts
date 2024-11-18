import { Component } from '@angular/core';
import { CcustomerService } from '../../services/ccustomer.service';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-customer-dashboard',
  standalone: true,
  imports: [CommonModule, DatePipe ],
  templateUrl: './customer-dashboard.component.html',
  styleUrl: './customer-dashboard.component.css'
})
export class CustomerDashboardComponent {
  constructor(private customerService: CcustomerService) {}
  cars: any = [];
  ngOnInit() {
    this.getAllCars();
 }

 getAllCars() {
   this.customerService.getAllCars().subscribe((res) => {
     console.log(res);
     res.forEach((element: { processedImg: string; returnedImage: string; }) => {
       element.processedImg = 'data:image/png;base64,' + element.returnedImage;
       this.cars.push(element);
     });
   })
 }

}
