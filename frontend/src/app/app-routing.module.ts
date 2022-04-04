import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {SignupComponent} from "./signup/signup.component";
import {RecoverPasswordComponent} from "./recover-password/recover-password.component";
import {TokenVerificationComponent} from "./token-verification/token-verification.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'recover', component: RecoverPasswordComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'accountVerification/:token', component: TokenVerificationComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
