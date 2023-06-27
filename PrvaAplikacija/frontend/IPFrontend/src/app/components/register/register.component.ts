import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import {UserService} from "../../services/user/user.service";
import { MatSnackBar } from '@angular/material/snack-bar';
import {Router} from "@angular/router";
import {User} from "../../customTypes/User";
import {StateService} from "../../globals/globals";
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  public form: FormGroup = new FormGroup({});
  // @ts-ignore
  public image: File | null;

  userData: User | undefined;
  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private snackBar: MatSnackBar,
              private router: Router,
              private stateService: StateService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required],
      city: [null, Validators.required],
      email: [null, Validators.required],
    });
  }

  handleImageChange(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    // @ts-ignore
    const fileList: FileList = inputElement.files;

    if (fileList.length > 0) {
      this.image = fileList[0];
    } else {
      this.image = null;
    }
  }

  public register(form: FormGroup) {
    // console.log(form.value);
    // @ts-ignore
    this.userService.register(form).subscribe(res => {
      console.log("ovo", res);
      if (this.image != null) { //dodaj avatar koji je odabran
        let formData: FormData = new FormData();
        formData.append("avatar", this.image);
        formData.append("userId", res.id);
        this.userService.addAvatar(formData).subscribe();
      } else if (this.image == null) { //dodaj defaultni avatar
        this.userService.addDefaultAvatar(res.id).subscribe();
      }
      this.stateService.setUserData({id: res.id, firstName: res.firstName, lastName: res.lastName, active: res.active});
      this.snackBar.open("Uspjesno ste se registrovali", undefined, {
        duration: 3000
      });

      this.router.navigate(['/activate-account']);
      // console.log("das", res);
    });

  }
}
