import { Component, OnInit } from '@angular/core';
import {AuthService} from "../shared/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-recover-password',
  templateUrl: './recover-password.component.html',
  styleUrls: ['./recover-password.component.css']
})
export class RecoverPasswordComponent implements OnInit {

  email: string;
  resetPasswordForm: any;
  errorMessage!: string;

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router, private formBuilder: FormBuilder, private toastr: ToastrService) {
    this.email = '';
  }

  ngOnInit(): void {
    this.resetPasswordForm = this.formBuilder.group({
      email: new FormControl('', [Validators.email, Validators.required ])
    });
  }

  resetPassword() {
    this.email = this.resetPasswordForm.get('email')?.value;

    this.authService.resetPassword(this.email).subscribe({
      complete: () => {this.toastr.success('A verification email has been sent. Please check your inbox and also the spam folder.')},
      error: (response) => {this.errorMessage = response.error},
      next: () => {this.router.navigate(['/'])}
    })
  }
}
