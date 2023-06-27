import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Supply} from "../../customTypes/Supply";
import {environment} from "../../../environments/environment.development";
import {Page} from "../../customTypes/Page";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SupplyService {

  constructor(private http: HttpClient) { }

  public addSupply(supply: any) {
    return this.http.post<Supply>(environment.apiUrl + "/supplies", supply, {headers: environment.headerOption});
  }
  public getAllActiveSupply() {
    return this.http.get<Supply>(environment.apiUrl + "/supplies/active-supply", {headers: environment.headerOption});
  }
  public getProducts(page: number, size: number, sort: string)  {
    // let params = new HttpParams();
    // params = params.append('page', String(page));
    // params = params.append('size', String(size));
    // params = params.append('sort', sort);
    return this.http.get(environment.apiUrl + `/supplies/active-supply?pageNo=${page}&pageSize=${size}`);
  }
}
