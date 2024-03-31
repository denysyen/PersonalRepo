import { Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';

// Routes need to be add it as objects in the Routes array 
//  each route must have a path: and componet: to work properly
export const routes: Routes = [
    {path: 'employees', component: EmployeeListComponent},
    {path: '', redirectTo: 'employees', pathMatch:'full'}
];
