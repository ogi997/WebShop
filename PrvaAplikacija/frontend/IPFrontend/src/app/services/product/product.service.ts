import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Product} from "../../customTypes/Product";
import {environment} from "../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient
              ) { }

  public addProduct(product: Product) {
    return this.http.post<Product>(environment.apiUrl + "/products", product, {headers: environment.headerOption});
  }
  public addProductImages(formData: FormData) {
    return this.http.post(environment.apiUrl + "/product-images", formData);
  }
  public getProductById(productId: number) {
    return this.http.get<Product>(environment.apiUrl + `/products/${productId}`, {headers: environment.headerOption});
  }
  public getCoverPhotoForProductId(productId: number) {
    return this.http.get(environment.apiUrl + `/product-images/${productId}/cover`, { responseType: 'blob' });
  }
  public getAllProductImageName(productId: number) {
    return this.http.get(environment.apiUrl + `/product-images/${productId}`, {headers: environment.headerOption});
  }
  public getImageByNameAndProductId(productId: number, imageName: string) {
    return this.http.get(environment.apiUrl + `/product-images/${productId}/${imageName}`, {responseType: 'blob'})
  }
  public getProductImageNonCover(productId: number) {
    return this.http.get(environment.apiUrl + `/product-images/${productId}/non-cover`, {headers: environment.headerOption});
  }
  public purchaseProduct(purchase: any) {
    return this.http.post(environment.apiUrl + "/purchases", purchase, {headers: environment.headerOption});
  }
  public getPurchaseForUserId(userId: any) {
    return this.http.get(environment.apiUrl + `/purchases/purchase-user/${userId}`, {headers: environment.headerOption});
  }
  public getAllProductsByUserId(userId: any) {
    return this.http.get(environment.apiUrl + `/products/user/${userId}`, {headers: environment.headerOption});
  }
  public getSupplyByProductId(productId: any) {
    return this.http.get(environment.apiUrl + `/supplies/product/${productId}`, {headers: environment.headerOption});
  }
  public changeActiveByProductId(productId: any, data: any) {
    return this.http.put(environment.apiUrl + `/supplies/change-active/${productId}`, data, {headers: environment.headerOption});
  }
  public filterByCategoryId(categoryId: any, condProduct: any, priceOd: any, priceDo: any, location: any,  page: any, size: any) {
    return this.http.get(environment.apiUrl + `/products/filter-category?categoryId=${categoryId}&conProduct=${condProduct}&priceOd=${priceOd}&priceDo=${priceDo}&location=${location}&page=${page}&size=${size}`);
  }
  public searchByTitle(title: string, page: any, size: any) {
    return this.http.get(environment.apiUrl + `/products/search-title?title=${title}&page=${page}&size=${size}`);
  }
  public deleteProduct(productId: any) {
    return this.http.patch(environment.apiUrl + `/products/delete/${productId}/1`, null);
  }
  public getFilteredData(categoryId: any, data: any, condProduct: any, priceOd: any, priceDo: any, location: any, page: any, size: any) {
    return this.http.post(environment.apiUrl + `/products/filter?categoryId=${categoryId}&conProduct=${condProduct}&priceOd=${priceOd}&priceDo=${priceDo}&location=${location}&page=${page}&size=${size}`, data);
  }

}
