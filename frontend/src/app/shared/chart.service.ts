import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {SignupRequestPayload} from "../signup/model/SignupRequestPayload";
import {Observable} from "rxjs";
import {AppConstants} from "./constants/AppConstants";

@Injectable({
  providedIn: 'root'
})
export class ChartService {

  constructor(private httpClient: HttpClient) { }

  getOhlcvChart(): Observable<any> {

    return this.httpClient.get<any>(AppConstants.OHLCV_ENDPOINT);
  }
}
