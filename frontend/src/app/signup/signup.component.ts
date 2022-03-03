import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {SignupRequestPayload} from "./model/SignupRequestPayload";
import {Router} from "@angular/router";
import {AuthService} from "../shared/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm: any;
  signupRequestPayload: SignupRequestPayload;
  submitted = false;
  success = '';
  // username: string = '';
  // password: string = '';

  // constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder, private _snackBar: MatSnackBar) {
  //   this.signupRequestPayload = {
  //     username: '',
  //     email: '',
  //     password: ''
  //   };
  // }
  constructor(private router: Router, private formBuilder: FormBuilder) {
    this.signupRequestPayload = {
      email: '',
      password: '',
      confirmPassword: ''
    };
  }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6)])
    })
  }

  confirmPassword() {
    // return this.signupForm.value['password'].valid;
    return (this.signupForm.value['password'] === this.signupForm.value['confirmPassword']) && this.signupForm.value['password'].touched && this.signupForm.value['confirmPassword'].touched
  }

  signup(signupForm: any) {
    console.log("BAAA")
    this.signupRequestPayload.email = signupForm.email;
    this.signupRequestPayload.password = signupForm.password;
    this.submitted = true;

    // stop here if form is invalid
    if (this.signupForm.invalid) {
      this.signupForm.getvalid
      console.log("adsfa")

      return;
    }

    this.success = JSON.stringify(this.signupForm.value);

    console.log(this.signupRequestPayload)

  }


}
