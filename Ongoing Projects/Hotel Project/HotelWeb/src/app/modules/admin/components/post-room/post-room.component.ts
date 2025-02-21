import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AdminService } from '../../admin-services/admin.service';
import { Observable, Observer } from 'rxjs';

@Component({
  selector: 'app-post-room',
  imports: [],
  templateUrl: './post-room.component.html',
  styleUrl: './post-room.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA ]
})
export class PostRoomComponent {

  roomDetailsForm!: FormGroup;

  constructor( private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router,
    private adminservice: AdminService
  ) {
    this.roomDetailsForm = this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      price: ['', Validators.required]
    })
  }

  submitForm() {
    console.log("Start of the posting  with value ==== ", this.adminservice);
    this.adminservice.postRoomDetails(this.roomDetailsForm.value).subscribe(res => {

      
      this.message.success(
        `Room Posted Successfully`, {nzDuration: 5000}
      );
      this.router.navigateByUrl('/admin/dashboard')
    }, error => {
      this.message.error(
        `${error.error}`, {nzDuration: 5000}
      )
    })
    // console.log("Start of the posting  .... ");
    // const postRoom: Observer<any> = {
    //   next: (res) => {
    //     this.message.success(
    //           `Room Posted Successfully`, {nzDuration: 5000}
    //         );
    //         this.router.navigateByUrl('/admin/dashboard')
    //   },
    //   error: (error) => {
    //     this.message.error(
    //           `${error.error}`, {nzDuration: 5000}
    //         )
    //   }, 
    //   complete: () => {console.log('Completed');}
    // }
    // console.log("Continuing  of the posting  .... ", postRoom);
    // this.adminservice.postRoomDetails(this.roomDetailsForm).subscribe(postRoom);
   // this.mySubscription = this.myObservable$.subscribe(myObserver);
  }

}
