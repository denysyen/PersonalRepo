import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [HttpClientModule, EmployeeListComponent,  CreateEmployeeComponent, FormsModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'messageAppFrontEnd';
}
