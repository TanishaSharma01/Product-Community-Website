import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../class/user';
import { RegisterService } from '../services/register.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = new User();
  msg='';

  loginform: FormGroup = new FormGroup({
    email: new FormControl('',[Validators.required, Validators.minLength(6), Validators.maxLength(25), Validators.pattern('[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$')]),
    password: new FormControl('',[Validators.required, Validators.minLength(4), Validators.maxLength(12), Validators.pattern('[a-zA-Z0-9]+$')])
  });

  get emailVal(){
    return this.loginform.get('email');
  }

  get passVal(){
    return this.loginform.get('password');
  }

  constructor(private _service:RegisterService, private _router :Router){}
  loginUser(){
    this._service.loginUserFromRemote(this.user).subscribe(
      data => {
        console.log('Logged User', data);
        this._router.navigate(['/'])
    },
      error => {
        console.log("exception occured");
        this.msg="Bad credentials, please enter valid email and password";
      }
    );
  }
}

