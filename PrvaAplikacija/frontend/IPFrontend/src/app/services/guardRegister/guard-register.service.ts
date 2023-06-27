import { Injectable } from '@angular/core';
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class GuardRegisterService {

  userData: User | undefined;
  constructor(private stateService: StateService,
              private router: Router
  ) {
    // this.userData = this.stateService.getUserData();
  }
  public canActivate() {
    this.userData = this.stateService.getUserData();

    if (this.userData === undefined) return true;
    else {
      this.router.navigate(['']);
      return false;
    }
  }
}
