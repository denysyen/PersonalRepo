import { Component, OnInit } from '@angular/core';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { StorageService } from '../../services/storage/storage.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NzSpinModule,
    NzFormModule,
    NzButtonModule,
    NzInputModule,
    NzLayoutModule,
    ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  isSpinning: boolean = false;
  loginForm!: FormGroup;

  constructor( private fb: FormBuilder, 
    private authService: AuthService, private router: Router, 
    private message: NzMessageService) { }

  ngOnInit() {
      this.loginForm = this.fb.group({
        email:[null, [Validators.email, Validators.required]],
        password:[null, [Validators.required]]
      })
  }
  
  login() {
    console.log(this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
      if(res.userId != null) {
        const user = {
          id: res.userId,
          role: res.userRole
        }
        StorageService.saveUser(user)
        StorageService.saveToken(res.jwt);
        if(StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("/admin/dashboard");
        } else if (StorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl("/customer/dashboard")
        } else {
          this.message.error("Bad credentials", {nzDuration: 3000});
        }
        
        
      }
    })
  }
}
