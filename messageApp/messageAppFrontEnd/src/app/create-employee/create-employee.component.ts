import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../employee.service';
// import { error } from 'console';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-employee',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'
})
export class CreateEmployeeComponent implements OnInit {
  employee: Employee = new Employee();

  constructor ( private employeeService: EmployeeService , private router: Router) { }

  ngOnInit(): void {   
  }

  saveEmployee() {
    this.employeeService.createEmployee(this.employee).subscribe( data => {
      console.log(data);
      this.goToEmployeesList();
    } //,  error => console.log(error)
    );

    
  }

  goToEmployeesList() {
    this.router.navigate(['/employees']);
  }
  onSubmit() {
    console.log(this.employee);
    this.saveEmployee();

  }

}
