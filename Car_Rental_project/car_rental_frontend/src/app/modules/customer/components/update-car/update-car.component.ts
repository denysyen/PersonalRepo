import { Component } from '@angular/core';
import { AdminService } from '../../../admin/services/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.css'
})
export class UpdateCarComponent {

  isSpinnig = false;
  updateForm!: FormGroup;
  activedRoute!: ActivatedRoute;
  existingImage: string | null =  null;
  constructor(private adminService: AdminService,
    private fb: FormBuilder) {}
  carId: number = this.activedRoute.snapshot.params["id"];
  listOfOption: Array<{label: string; value: string}> = [];
  listOfBrands = ["BMW", "AUDI", "FERRARI", "TESLA", "VOLVO", "TOYOTA", "HONDA", "FORD", "NISSAN", "HYUNDAI", "LEXUS", "KIA"];
  listOfType = ["Petrol", "Hybrid", "Diesel", "Electric", "CNG"];
  listOfColor = ["Red", "White", "Blue", "Black", "Orange", "Grey", "Silver"];
  listOfTransmission = ["Manual", "Automic"];

  ngOnInit() {
    this.updateForm = this.fb.group({
      name: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      year: [null, Validators.required]
    })
     this.getCarById();
  }
   getCarById() {
    this.isSpinnig = true;
    this.adminService.getCarById(this.carId).subscribe((res) => {
      console.log(res);
      const carDto = res;
      this.isSpinnig = false;
      this.existingImage = 'data:image/png;base64,' +res.returnedImage;
      this.updateForm.patchValue(carDto);
  
    })
   }

}
