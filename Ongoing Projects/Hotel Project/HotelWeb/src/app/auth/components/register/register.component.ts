import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth/auth.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';


@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule],  // ReactiveFormsModule is need it to use formGroup on HTML
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA ]
})
export class RegisterComponent {

   registerForms!: FormGroup;
   
   // the formBuilder is a tool that allow us to cuztomize
   // a form HTML template using attributes need and also 
   // implements a classic two-way binding between DOM and TS 
   constructor(private fb: FormBuilder, 
        private authService: AuthService,
        private message: NzMessageService,
        private router: Router
      ) { }

   ngOnInit() {
    this.registerForms = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, Validators.required],
      name: [null, Validators.required]
    })
   }

   submitForm() {
    this.authService.register(this.registerForms.value).subscribe({
      next: (res) => {
        if(res.id != null) {
          this.message.success("Signup successful", { nzDuration: 5000});
          this.router.navigateByUrl("/");
      }

      }, error: (error) =>{
        this.message.error(`${error.message}`, {nzDuration: 5000});
      }
    })
   }
}
