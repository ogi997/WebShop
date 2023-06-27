import {Component, OnInit} from '@angular/core';
import {StateService} from "../../globals/globals";
import {User} from "../../customTypes/User";
import {Router} from "@angular/router";
// import {Users} from '../../globals/globals';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  userData: User | undefined;
public constructor(private stateService: StateService,
                   private router: Router
                   ) {
}
  ngOnInit(): void {
    //global variable
    this.userData = this.stateService.getUserData();
    this.stateService.userDataChanged.subscribe((value) => {
      this.userData = value;
    });
  }

  public logout() {
  this.stateService.setUserData(undefined);
  this.router.navigate(['/']);
  }
}
