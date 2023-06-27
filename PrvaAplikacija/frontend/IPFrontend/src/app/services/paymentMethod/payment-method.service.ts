import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class PaymentMethodService {

  constructor(private http: HttpClient) { }

  public getPaymenthMethodById(id: number) {
    return this.http.get(environment.apiUrl + `/payment-methods/${id}`, {headers: environment.headerOption});
  }
}
