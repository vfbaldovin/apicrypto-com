import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AppConstants} from "./constants/AppConstants";
import {RecoverPasswordRequest} from "../reset-password/model/recover-password-request.payload";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  constructor(private http: HttpClient) { }

  verifyUser(token: string): Observable<any> {
    return this.http.post(AppConstants.API_ENDPOINT + '/auth/accountVerification', token);
  }

  changePassword(recoverPasswordRequest: RecoverPasswordRequest): Observable<any> {
    return this.http.post(AppConstants.API_ENDPOINT + '/auth/changePassword', recoverPasswordRequest);
  }
}
