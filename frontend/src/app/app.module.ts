import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { FooterComponent } from './footer/footer.component';
import { RecoverPasswordComponent } from './recover-password/recover-password.component';
import {BrowserAnimationsModule, NoopAnimationsModule} from '@angular/platform-browser/animations';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {HttpClientModule} from "@angular/common/http";
import {NgxWebstorageModule} from "ngx-webstorage";
import {ToastrModule} from "ngx-toastr";
import {NgApexchartsModule} from "ng-apexcharts";
import { TokenVerificationComponent } from './token-verification/token-verification.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {MenubarModule} from "primeng/menubar";
import {SharedModule} from "primeng/api";
import {InputTextModule} from "primeng/inputtext";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatButtonModule} from "@angular/material/button";
import { FlexLayoutModule } from '@angular/flex-layout';
import {MatButtonToggleModule} from "@angular/material/button-toggle";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    SignupComponent,
    FooterComponent,
    RecoverPasswordComponent,
    TokenVerificationComponent,
    ResetPasswordComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NoopAnimationsModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        ReactiveFormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        NgxWebstorageModule.forRoot(),
        ToastrModule.forRoot(),
        NgApexchartsModule,
        MenubarModule,
        SharedModule,
        InputTextModule,
        FormsModule,
        MatToolbarModule,
        MatSidenavModule,
        MatButtonModule,
        FlexLayoutModule,
        MatButtonToggleModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
