import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {SignupRequestPayload} from "../signup/model/SignupRequestPayload";
import {map, Observable, tap, throwError} from "rxjs";
import {LoginResponse} from "../login/model/login-response.payload";
import {LocalStorageService} from "ngx-webstorage";
import {LoginRequestPayload} from "../login/model/login-request.payload";
import {AppConstants} from "./constants/AppConstants";
import {RecoverPasswordRequest} from "../reset-password/model/recover-password-request.payload";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();

  refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName()
  }

  constructor(private httpClient: HttpClient, private localStorage: LocalStorageService) {
  }

  signup(signupRequestPayload: SignupRequestPayload): Observable<any> {
    return this.httpClient.post(AppConstants.API_ENDPOINT + '/auth/signup', signupRequestPayload, { responseType: 'text' });
  }

  resetPassword(email: string): Observable<any> {
    return this.httpClient.post(AppConstants.API_ENDPOINT + '/auth/reset', email, { responseType: 'text' });
  }

  login(loginRequestPayload: LoginRequestPayload): Observable<boolean> {
    return this.httpClient.post<LoginResponse>(AppConstants.API_ENDPOINT + '/auth/login',
      loginRequestPayload).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('username', data.username);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);

      this.loggedIn.emit(true);
      this.username.emit(data.username);
      return true;
    }));
  }

  getJwtToken() {
    return this.localStorage.retrieve('authenticationToken');
  }

  refreshToken() {
    return this.httpClient.post<LoginResponse>(AppConstants.API_ENDPOINT + '/auth/refresh/token',
      this.refreshTokenPayload)
      .pipe(tap(response => {
        this.localStorage.clear('authenticationToken');
        this.localStorage.clear('expiresAt');

        this.localStorage.store('authenticationToken',
          response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt);
      }));
  }

  verifyUser(token: string): Observable<any> {
    return this.httpClient.post(AppConstants.API_ENDPOINT + '/auth/accountVerification', token);
  }

  changePassword(recoverPasswordRequest: RecoverPasswordRequest): Observable<any> {
    return this.httpClient.post(AppConstants.API_ENDPOINT + '/auth/changePassword', recoverPasswordRequest);
  }

  logout() {
    this.httpClient.post(AppConstants.API_ENDPOINT + '/auth/logout', this.refreshTokenPayload,
      { responseType: 'text' }).subscribe({
        next: data => {console.log(data)},
        error: error => {throwError(error)}
      });
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }
  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
