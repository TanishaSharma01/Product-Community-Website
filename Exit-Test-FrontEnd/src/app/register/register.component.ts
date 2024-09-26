import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../class/user';
import { RegisterService } from '../services/register.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = new User();
  msg='';
  registerform: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(20), Validators.pattern('[a-zA-Z]+$')]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(20), Validators.pattern('[a-zA-Z]+$')]),
    email: new FormControl('',[Validators.required, Validators.minLength(6), Validators.maxLength(25), Validators.pattern('[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$')]),
    password: new FormControl('',[Validators.required, Validators.minLength(4), Validators.maxLength(12), Validators.pattern('[a-zA-Z0-9]+$')]),
    // cpassword: new FormControl('', [Validators.required, Validators.minLength(4), Validators.maxLength(2), Validators.pattern('[a-zA-Z0-9]+$'),],),
  }, );

  get fnVal(){
    return this.registerform.get('firstName');
  }

  get lnVal(){
    return this.registerform.get('lastName');
  }

  get emailVal(){
    return this.registerform.get('email');
  }

  get passVal(){
    return this.registerform.get('password');
  }

  // get cpassVal(){
  //   return this.registerform.get('cpassword');
  // }

  constructor(private _service:RegisterService, private _router :Router){}
  registerUser(){
    this._service.registerUserFromRemote(this.user).subscribe(
      data => {
        console.log("registration successful");
        this._router.navigate(['/login'])
    },
      error => {
        console.log("exception occured");
        this.msg="User for this email id already exists";
      }
    );
  }
}

