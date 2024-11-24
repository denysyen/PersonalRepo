import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { NgZorroImportsModule } from '../../../../NgZorroImportsModule';
import { CommonModule } from '@angular/common';
import { AdminService } from '../../services/admin.service';
import { CcustomerService } from '../../../customer/services/ccustomer.service';

@Component({
  selector: 'app-search-car',
  standalone: true,
  imports: [NgZorroImportsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './search-car.component.html',
  styleUrl: './search-car.component.css'
})
export class SearchCarComponent {
  searchCarForm!: FormGroup;
  isSpinning = false;
  cars: any = [];
  listOfOptions: Array<{label:string; value: string }> = [];
  listOfBrands = ["BMW", "AUDI", "FERRARI", "TESLA", "VOLVO", "TOYOTA", "HONDA", "FORD", "NISSAN", "HYUNDAI", "LEXUS", "KIA"];
  listOfTypes = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG"];
  listOfColors = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Manual", "Automic"];

  constructor(private fb: FormBuilder, private service: CcustomerService) {
    this.searchCarForm = this.fb.group({
      brand: [null],
      type: [null],
      transmission: [null],
      color: [null]
    })
  }

  searchCar() {
    this.isSpinning = true;
    console.log(this.searchCarForm.value);
    this.service.searchCar(this.searchCarForm.value).subscribe((res) => {
      res.carDtoList.forEach((element: {processedImg: string; returnedImage: string;}) => {
        element.processedImg = 'data:image/jpeg;base64,' + element.returnedImage;
        this.cars.push(element);
      });
      this.isSpinning = false;
    })
  }


}
