import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {FormGroup} from "@angular/forms";
import {environment} from "./../../../environments/environment.development";
import {catchError, throwError} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,
              private snackBar: MatSnackBar,
              // private stateService: StateService
              ) { }

  public register(form: FormGroup): any {
    // form.value.deleted = 0;
    return this.http.post(environment.apiUrl + "/users", form.value, {
      headers: environment.headerOption
    }).pipe(
      catchError(
        (e: any) => {
          if (e.status === 403)
            this.snackBar.open("Korisnicko ime ili email su vec zauzeti.", undefined, {
              duration: 5000
            });
          return throwError(e);
        }
    ));
  }
  public addAvatar(formData: FormData): any {
    return this.http.post(environment.apiUrl + "/avatars", formData);
  }
  public addDefaultAvatar(userId: number) {
    return this.http.post(environment.apiUrl + `/avatars/${userId}`, null, {headers: environment.headerOption});
  }
  public getAvatarByUserId(userId: number | undefined) {
    return this.http.get(environment.apiUrl + `/avatars/${userId}`, {responseType: 'blob'});
  }
  public getUserById(userId: number | undefined) {
    return this.http.get(environment.apiUrl + `/users/${userId}`, {headers: environment.headerOption});
  }
  public updateUserByUserId(userId: number | undefined, data: any) {
    return this.http.put(environment.apiUrl + `/users/${userId}`, data, {headers: environment.headerOption});
  }
  public updateUserAvatar(formData: FormData): any {
    return this.http.put(environment.apiUrl + "/avatars", formData);
  }
  public loginUser(data: any) {
    return this.http.post(environment.apiUrl + "/users/loginUsers", data, {headers: environment.headerOption});
  }
  public activateAccount(data: any, userId: any) {
    return this.http.post(environment.apiUrl + `/users/activate-user/${userId}`, data, {headers: environment.headerOption})
      .pipe(catchError((e: any) => {

        this.snackBar.open("Vasa aktivacija nije uspjela.", undefined, {
          duration: 3000
        });
        // this.stateService.setUserData(undefined);
        return throwError(e);
      })
      );
  }
  public generateNewPinCode(userId: any) {
    return this.http.post(environment.apiUrl + `/users/generate-pincode/${userId}`, null, {headers: environment.headerOption});
  }
}
