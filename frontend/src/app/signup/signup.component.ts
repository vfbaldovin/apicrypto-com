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
  username: string = '';
  password: string = '';

  // constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder, private _snackBar: MatSnackBar) {
  //   this.signupRequestPayload = {
  //     username: '',
  //     email: '',
  //     password: ''
  //   };
  // }
  constructor(private router: Router, private formBuilder: FormBuilder) {
    this.signupRequestPayload = {
      username: '',
      email: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      username: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', Validators.required)
    })
  }

  signup(signupForm: any) {
    this.signupRequestPayload.username = signupForm.username;
    this.signupRequestPayload.password = signupForm.password;

  }
}
