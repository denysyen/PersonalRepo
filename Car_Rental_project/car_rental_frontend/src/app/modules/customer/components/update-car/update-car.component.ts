import { Component } from '@angular/core';
import { AdminService } from '../../../admin/services/admin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.css'
})
export class UpdateCarComponent {
  activedRoute!: ActivatedRoute;
  existingImage: string | null =  null;
  constructor(private adminService: AdminService) {}
  carId: number = this.activedRoute.snapshot.params["id"];
  
  ngOnInit() {
     this.getCarById();
  }
   getCarById() {
    this.adminService.getCarById(this.carId).subscribe((res) => {
      console.log(res);
      const carDto = res;
      this.existingImage = 'data:image/png;base64,' +res.returnedImage;
    })
   }

}
