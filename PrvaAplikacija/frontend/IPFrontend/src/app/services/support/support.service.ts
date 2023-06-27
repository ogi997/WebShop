import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class SupportService {

  constructor(private http: HttpClient) { }

  public addMessage(data: any) {
    return this.http.post(environment.apiUrl + "/messages", data, {headers: environment.headerOption});
  }
}
