import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() {

  }
  hide = true;

  get emailInput() { return this.signin.get('email'); }
  get passwordInput() { return this.signin.get('password'); }

  signin: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.email, Validators.required ]),
    password: new FormControl('', [Validators.required, Validators.min(3) ])
  });


  ngOnInit(): void {

  }

}
