import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../shared/auth.service";

@Component({
  selector: 'app-token-verification',
  templateUrl: './token-verification.component.html',
  styleUrls: ['./token-verification.component.css']
})
export class TokenVerificationComponent implements OnInit {

  token!: string;

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router, private toastr: ToastrService) {
    this.token = this.activatedRoute.snapshot.params['token'];
  }

  ngOnInit(): void {
    this.authService.verifyUser(this.token).subscribe({
      complete: () => {this.toastr.success('Account successfully activated.')},
      error: () => {this.toastr.error('The token is invalid.'); this.router.navigate(['/login'])},
      next: () => this.router.navigate(['/login'])
    });
  }

}
