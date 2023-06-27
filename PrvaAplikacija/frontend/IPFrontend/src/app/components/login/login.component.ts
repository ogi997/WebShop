import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
// import {Users} from '../../globals/globals';
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  public form: FormGroup = new FormGroup({});

  userData: User | undefined;

  public constructor(private formBuilder: FormBuilder,
                     private changeDetectorRef: ChangeDetectorRef,
                     private stateService: StateService,
                     private snackBar: MatSnackBar,
                     private router: Router,
                     private userService: UserService
                     ) {
  }

  ngOnInit(): void {
    // forma
    this.form = this.formBuilder.group({
      email: [null, Validators.required],
      password: [null, Validators.required]
    });

  //global variable
    this.userData = this.stateService.getUserData();
    this.stateService.userDataChanged.subscribe((value) => {
      this.userData = value;
    });
  }

  public loginUser() {

    this.userService.loginUser(this.form.value).subscribe((result: any) => {
      this.stateService.setUserData({id: result.id, firstName: result.firstName, lastName: result.lastName, active: result.active});
      this.snackBar.open("Uspjesno ste se prijavili.", undefined, {
        duration: 3000
      });
      if (result.active === 0) {
        this.userService.generateNewPinCode(result.id).subscribe((result) => {
          this.router.navigate(['/activate-account']);
        });

      } else {
        this.router.navigate(['']);
      }
    });

    // console.log(this.form.value);
    // if (this.form.value.email === 'tomanic.ogi@gmail.com' && this.form.value.password === 'lozinka123') {
    //   //uspjesna prijava
    //   this.stateService.setUserData({id: 1, firstName: "Ognjen", lastName: "Tomanic", active: 0});
    //   if (this.userData?.active === 0)
    //     this.router.navigate(['activate-account']);
    //   else
    //     this.router.navigate(['']);
    //   // this.snackBar.open("Uspjesno ste se prijavili!", undefined, {
    //   //   duration: 3000
    //   // });
    //   // this.router.navigate(['/']);
    // } else if (this.form.value.email === 'marko.markovic@gmail.com' && this.form.value.password === 'loz123') {
    //   this.stateService.setUserData({id: 2, firstName: "Marko", lastName: "Markovic", active: 0});
    //
    //   this.snackBar.open("Uspjesno ste se prijavili!", undefined, {
    //     duration: 3000
    //   });
    //   this.router.navigate(['/']);
    }
}
