import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './auth/components/register/register.component';
import { LoginComponent } from './auth/components/login/login.component';
import { AdminComponent } from './modules/admin/admin.component';

export const routes: Routes = [
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'customer', loadChildren: () => import('./modules/customer/customer.module').then(m => m.CustomerModule) },
    {path: 'admin', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) }
];
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  declarations: [AdminComponent]
})
export class AppRoutingModule { }