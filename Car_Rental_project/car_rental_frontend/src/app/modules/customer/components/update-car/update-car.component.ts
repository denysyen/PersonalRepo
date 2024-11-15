import { Component } from '@angular/core';
import { AdminService } from '../../../admin/services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';


@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.css'
})
export class UpdateCarComponent {

  isSpinnig = false;
  updateForm!: FormGroup;
  imgChange: boolean = false;
  selectedFile: any;
  imagePreview!:string | ArrayBuffer | null;
  activedRoute!: ActivatedRoute;
  existingImage: string | null =  null;
  constructor(private adminService: AdminService, private message: NzMessageService,
    private fb: FormBuilder, private router: Router) {}
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
   updateCar() {
      this.isSpinnig = true;
      const formData: FormData = new FormData();
      if(this.imgChange && this.selectedFile) {
        formData.append('img', this.selectedFile);
      }   
      formData.append('brand',  this.updateForm.get('brand')?.value);        
      formData.append('name', this.updateForm.get('name')?.value);
      formData.append('type', this.updateForm.get('type')?.value);
      formData.append('color', this.updateForm.get('color')?.value);
      formData.append('year', this.updateForm.get('year')?.value);
      formData.append('transmission', this.updateForm.get('transmission')?.value);
      formData.append('description', this.updateForm.get('description')?.value);
      formData.append('price', this.updateForm.get('price')?.value);
      console.log(formData);
      this.adminService.updateCar(this.carId, formData).subscribe((res) => {
        this.message.success("Car updated successfullu", {nzDuration : 3000});
        this.isSpinnig = false;
          this.router.navigateByUrl("/admin/dashboard");
          console.log(res);
      }, error => {
        this.message.error("Error while updating car", {nzDuration: 3000})
      });

   }

   onFileSelected(event:any) {
     this.selectedFile = event.target.files[0];
     this.imgChange = true;
     this.existingImage = null;
     this.previewImage();
   }

   previewImage() {
    const reader = new FileReader();
    reader.onload = () => {
        this.imagePreview = reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
   }

}
