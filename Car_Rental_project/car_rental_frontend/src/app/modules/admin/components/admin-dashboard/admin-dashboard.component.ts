import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { subscribe } from 'diagnostics_channel';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {

  constructor(private adminService: AdminService,
    private message: NzMessageService) {}
 cars: any = [];
  ngOnInit() {
     this.getAllCars();
  }

  getAllCars() {
    this.adminService.getAllCars().subscribe((res) => {
      console.log(res);
      res.forEach((element: { processedImg: string; returnedImage: string; }) => {
        element.processedImg = 'data:image/png;base64,' + element.returnedImage;
        this.cars.push(element);
      });
    })
  }
  deleteCar(id: number){
    console.log(id);
    this.adminService.deleteCar(id).subscribe((res) => {
      this.getAllCars();
      this.message.success("Car deleted successfully", {nzDuration: 3000});
    });
  }
}
