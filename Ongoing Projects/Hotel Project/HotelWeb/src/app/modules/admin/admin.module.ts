import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PostRoomComponent } from './components/post-room/post-room.component';
import { NgZorroAntModule } from '../../NgZorroAntModule';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
   // AdminComponent,
    DashboardComponent,
    // PostRoomComponent
  ],

  imports: [
    CommonModule,
    AdminRoutingModule,
    NgZorroAntModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class AdminModule { }
