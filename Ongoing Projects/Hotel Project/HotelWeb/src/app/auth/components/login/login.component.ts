import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../service/auth/auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { Inject } from '@angular/core';
import { UserStotageService } from '../../service/storage/user-stotage.service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
    schemas: [CUSTOM_ELEMENTS_SCHEMA ]
})
export class LoginComponent {
  loginForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
    })
  }

  submitForm(){
    this.authService.login(this.loginForm.value).subscribe(res => {
      if(res.userId != null) {
        const user = {
          id: res.userId,
          role: res.userRole
        }
        UserStotageService.saveUser(user);
        UserStotageService.saveToken(res.jwt);
      }
    }, error => {
      this.message.error( `Bad credentials`, { nzDuration: 5000})
    })
  }
}
