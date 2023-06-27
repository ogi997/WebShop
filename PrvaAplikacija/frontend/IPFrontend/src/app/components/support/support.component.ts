import {Component, OnInit} from '@angular/core';
import {SupportService} from "../../services/support/support.service";
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit{
  userData: User | undefined;
  selectedMessage: string | undefined;
  public constructor(private stateService: StateService,
                     private supportService: SupportService,
                     private snackBar: MatSnackBar
  ) {
  }
  ngOnInit(): void {
    this.userData = this.stateService.getUserData();
  }

  public posaljiPoruku() {
    if (this.selectedMessage === undefined) {
      this.snackBar.open("Morate da unesete poruku.", undefined, {
        duration: 3000
      });
      return;
    }
    const data = {
      fkU: this.userData?.id,
      text: this.selectedMessage,
      status: 0
    }
    this.supportService.addMessage(data).subscribe((result: any) => {
      this.snackBar.open("Uspjesno ste poslali poruku", undefined, {
        duration: 3000
      });
      this.selectedMessage = '';
    });
  }
}
