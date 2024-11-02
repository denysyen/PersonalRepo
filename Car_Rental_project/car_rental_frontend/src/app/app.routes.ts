import {RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/component/signup/signup.component';
import { LoginComponent } from './auth/component/login/login.component';

import { NgModule } from '@angular/core';
export const routes: Routes = [
    {path: "register", component: SignupComponent},
    {path: "login", component: LoginComponent}
];
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
