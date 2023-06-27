import {Component, OnDestroy, OnInit} from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {UserService} from "../../services/user/user.service";
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";
import {Router} from "@angular/router";

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit, OnDestroy{
  selectedPin: any | undefined;

  userData: User | undefined;

  isSuccess: boolean = false;

  public constructor(private snackBar: MatSnackBar,
                     private userService: UserService,
                     private stateService: StateService,
                     private router: Router
                     ) {
  }
  ngOnInit(): void {
    this.userData = this.stateService.getUserData();
  }
  ngOnDestroy() {
    if (!this.isSuccess)
    this.stateService.setUserData(undefined);
  }

  public checkPinCode() {
    let data = {
      pinCode: this.selectedPin
    };

    this.userService.activateAccount(data, this.userData?.id).subscribe((result: any) => {
      this.snackBar.open("Uspjesno ste aktivirali Vas nalog.", undefined, {
        duration: 3000
      });
      this.isSuccess = true;
      this.stateService.setUserData({id: result.id, firstName: result.firstName, lastName: result.lastName, active: result.active});
      this.router.navigate(['']);
    });
    // console.log(this.selectedPin);
  }

}
