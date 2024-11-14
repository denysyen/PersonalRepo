import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-employee',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './update-employee.component.html',
  styleUrl: './update-employee.component.css'
})
export class UpdateEmployeeComponent implements OnInit {
  
  id: number =  0;
  employee: Employee = new Employee();

  constructor ( private employeeService: EmployeeService , private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    // strategy to recover the Id from the router each time
    // one access a particular employee
    this.id = this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe( data => {
      this.employee = data;
    })
  }

  saveEmployee() {
    this.employeeService.createEmployee(this.employee).subscribe( data => {
      console.log(data);
    } //,  error => console.log(error)
    );
  }

  goToEmployeesList() {
    this.router.navigate(['/employees']);
  }

  onSubmit() {
    console.log(this.employee);
    this.employeeService.updateEmployee(this.id, this.employee).subscribe(data => {
      this.goToEmployeesList();
    });

  }
 
}
