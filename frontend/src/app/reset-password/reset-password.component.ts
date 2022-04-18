import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {RecoverPasswordRequest} from "./model/recover-password-request.payload";
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {AuthService} from "../shared/auth.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  recoverPasswordForm: any;
  token!: string;
  recoverPasswordRequest: RecoverPasswordRequest;
  errorMessage!: string;

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private toastr: ToastrService) {
    this.token = this.activatedRoute.snapshot.params['token'];
    this.recoverPasswordRequest = {
      token: '',
      password: ''
    };
  }

  ngOnInit(): void {
    this.recoverPasswordForm = this.formBuilder.group({
      password: new FormControl('', [Validators.required, Validators.minLength(8)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(8)])
    })
  }

  resetPassword() {
    this.recoverPasswordRequest.token = this.token;
    this.recoverPasswordRequest.password = this.recoverPasswordForm.get('password')?.value;
      this.authService.changePassword(this.recoverPasswordRequest).subscribe({
        complete: () => {this.toastr.success('Password changed successfully.')},
        error: () => {this.toastr.error('The token is invalid.'); this.router.navigate(['/login'])},
        next: () => this.router.navigate(['/login'])
      });
  }
}
