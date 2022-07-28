import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {LoginRequestPayload} from "./model/login-request.payload";
import {throwError} from "rxjs";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequestPayload: LoginRequestPayload;
  loginForm: any;
  hide = true;
  isError: boolean = false;
  errorMessage!: string;

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private toastr: ToastrService) {
    this.loginRequestPayload = {
      email: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.loginForm =  this.formBuilder.group({
      email: new FormControl('', [Validators.email, Validators.required ]),
      password: new FormControl('', [Validators.required, Validators.min(8)])
    });
  }

  login() {
    this.loginRequestPayload.email = this.loginForm.get('email')?.value;
    this.loginRequestPayload.password = this.loginForm.get('password')?.value;

    this.authService.login(this.loginRequestPayload).subscribe({
      next: () => {
        this.isError = false;
        this.router.navigateByUrl('dashboard');
        this.toastr.success('Login Successful') //@todo add material snacbar
      },
      error: err => {
        this.isError = true;
        this.errorMessage = err.error;
        throwError(err);
      }
    })
  }
}
