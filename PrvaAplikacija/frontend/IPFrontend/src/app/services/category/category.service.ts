import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";
import {Category} from "../../customTypes/Category";
import {Attribute} from "../../customTypes/Attribute";
import {AttributeValues} from "../../customTypes/AttributeValues";

@Injectable({
  providedIn: 'root'
})
// za rad sa kategorijama i atributima kategorija
export class CategoryService {

  constructor(private http: HttpClient) { }

  public getAllCategory() {
    return this.http.get<Category[]>(environment.apiUrl + "/categories", {headers: environment.headerOption});
  }
  public getAttributesForCategoryId(categoryId: number) {
    return this.http.get<Attribute[]>(environment.apiUrl + `/attributes/category/${categoryId}`, {headers: environment.headerOption});
  }
  public addAttributeValues(attValue: AttributeValues) {
    return this.http.post<AttributeValues>(environment.apiUrl + "/attribute-values", attValue, {headers: environment.headerOption});
  }
  public getAttributeById(attributeId: number) {
    return this.http.get(environment.apiUrl + `/attributes/${attributeId}`, {headers: environment.headerOption});
  }
  public getAttributeValueForProductId(productId: number) {
    return this.http.get(environment.apiUrl + `/attribute-values/product/${productId}`);
  }
  public getCategoryById(categoryId: number) {
    return this.http.get(environment.apiUrl + `/categories/${categoryId}`, {headers: environment.headerOption});
  }
}
