import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {BackendService} from '../backend.service';

class ILogin {
  login: string;
  password: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  model: ILogin = {login: 'admin', password: 'admin123'};
  message: string;

  constructor(
    private formBuilder: FormBuilder, private router: Router, public contactService: BackendService
  ) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      login: [null, [Validators.required]],
      password: [null, Validators.required]
    });
  }

  // tslint:disable-next-line:typedef
  submit() {
    if (!this.loginForm.valid) {
      return;
    } else {
      this.contactService.checkLogin(this.loginForm.controls.login.value, this.loginForm.controls.password.value)
        .subscribe(res => {
          if (res === true) {
            console.log('Login successful');
            localStorage.setItem('isLoggedIn', 'true');
            localStorage.setItem('token', this.loginForm.controls.login.value);

            this.router.navigate(['/dashboard']);
          } else {
            this.message = 'Please check your login and password';
          }
        });
    }
  }
}
