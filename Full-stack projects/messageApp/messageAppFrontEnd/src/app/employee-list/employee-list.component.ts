import { Component, OnInit  } from '@angular/core';
import { Employee } from '../employee';
import { CommonModule } from '@angular/common'
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

/**
 * Class to implement the logic of the component data and 
 * the properties that need to be export towards the HTML
 */

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],   // one needs CommonModule to get access to ngFor and ngIf in the HTML 
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})

  
export class EmployeeListComponent implements OnInit {

  employees: Employee[] = [];  // in a non standAlone (Angular 17+) situation one need to initilaze the properties
   // injecting the service into the component 
  constructor( private employeeService: EmployeeService, private router: Router) {}

  // calling the data from the service in the Init()
  ngOnInit(): void {
    this.getEmployees();
   
  }
  // Using the injected service through a custom component method
  private getEmployees() {
    this.employeeService.getEmployeesListt().subscribe( data => {
      this.employees =  data;
    })
  }

  updateEmployee(id: number) {
    this.router.navigate(['update-employee', id]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(data => {
      console.log(data);
      this.getEmployees();
    })
  }
}
