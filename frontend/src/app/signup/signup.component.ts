import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {SignupRequestPayload} from "./model/SignupRequestPayload";
import {Router} from "@angular/router";
import {AuthService} from "../shared/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ToastrService} from "ngx-toastr";

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
    console.log("BAAA")
    this.signupRequestPayload.email = this.signupForm.get('email')?.value;
    this.signupRequestPayload.password = this.signupForm.get('password')?.value;
    this.submitted = true;

    // this.authService.signup(this.signupRequestPayload)
    //   .subscribe(data => {
    //     this.router.navigate(['/login'],
    //       {queryParams: {registered: 'true'}});
    //   }, error => {
    //     console.log(error);
    //     this.toastr.error('Registration Failed! Please try again');
    //   });


    // this.authService.signup(this.signupRequestPayload).subscribe(
    //   result => {
    //     this.toastr.success('Successfully updated');
    //   }, error => {
    //     this.toastr.error(error.error.message);
    //   }
    //   //     result?:
    // )

    this.authService.signup(this.signupRequestPayload).subscribe({
      complete: () => { console.log("completeHandler"), this.toastr.success('Successfully updated')}, // completeHandler
      error: error => {
        console.log(error);
        this.toastr.error('Registration Failed! Please try again');
      },    // errorHandler
      next: () => this.router.navigate(['/login'], { queryParams: { registered: 'true' }})  // nextHandler
      });
    }

}
