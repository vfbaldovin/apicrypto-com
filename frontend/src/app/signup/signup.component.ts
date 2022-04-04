import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {SignupRequestPayload} from "./model/SignupRequestPayload";
import {Router} from "@angular/router";
import {AuthService} from "../shared/auth.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm: any;
  signupRequestPayload: SignupRequestPayload;
  errorMessage = null;

  constructor(private authService: AuthService, private router: Router, private formBuilder: FormBuilder, private toastr: ToastrService) {
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
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6)]),
      acceptPolicy: new FormControl('', Validators.requiredTrue)
    })
  }


  signup() {
    this.signupRequestPayload.email = this.signupForm.get('email')?.value;
    this.signupRequestPayload.password = this.signupForm.get('password')?.value;

    this.authService.signup(this.signupRequestPayload).subscribe({
      complete: () => {this.toastr.success('An activation email has been sent. Please check your inbox and also the spam folder.')},
      error: (response) => {this.errorMessage = response.error},
      next: () => this.router.navigate(['/login'])  // nextHandler
      });
    }

}
