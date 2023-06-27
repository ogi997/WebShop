import {Component, OnInit} from '@angular/core';
import {StateService} from "../../globals/globals";
import {UserService} from "../../services/user/user.service";
import {User} from "../../customTypes/User";
import {FormBuilder, FormControl, FormControlName, FormGroup, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  userData: User | undefined;
  // loadUserData: any | undefined;
  avatar: any;
  selectedNewAvatar: any;
  changeImage: any;
  public form: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    password: new FormControl(''),
    city: new FormControl(''),
    email: new FormControl('')
  });

  public constructor(private stateService: StateService,
                     private userService: UserService,
                     private formBuilder: FormBuilder,
                     private snackBar: MatSnackBar
                     ) {
  }
  ngOnInit(): void {
    this.userData = this.stateService.getUserData();

    this.getData();

    this.form = this.formBuilder.group({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      password: new FormControl(''),
      city: new FormControl(''),
      email: new FormControl('')
    })

    // this.getData();
  }

  private getData() {
   this.getAvatar();
   this.getDataOfUser();
  }
  private getAvatar() {
    this.userService.getAvatarByUserId(this.userData?.id).subscribe((result) => {
      this.avatar = URL.createObjectURL(result);
    });
  }
  private getDataOfUser() {
    this.userService.getUserById(this.userData?.id).subscribe((result: any) => {
      console.log("resultUser", result);
      this.form = this.formBuilder.group({
        firstName: [result.firstName, Validators.required],
        lastName: [result.lastName, Validators.required],
        password: [result.password, Validators.required],
        city: [result.city, Validators.required],
        email: [result.email, Validators.required]
      })
      // this.loadUserData = result;
    });
  }

  public updateProfile() {
    this.userService.updateUserByUserId(this.userData?.id, this.form.value).subscribe((result: any) => {
      this.snackBar.open("Uspjesno ste azurirali nalog", undefined, {
        duration: 3000
      });
      this.getDataOfUser();
    });
    // console.log(this.form.value);
  }

  handleImageChange(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    // @ts-ignore
    const fileList: FileList = inputElement.files;

    if (fileList.length > 0) {
      this.selectedNewAvatar = fileList[0];
    } else {
      this.selectedNewAvatar = null;
    }
  }

  public updateAvatar() {
    console.log(this.selectedNewAvatar);
    if (this.selectedNewAvatar === undefined) {
      this.snackBar.open("Morate izabrati novi avatar", undefined, {
        duration: 3000
      });
      return;
    }

    let formData: FormData = new FormData();
    formData.append('avatar', this.selectedNewAvatar);
    // @ts-ignore
    formData.append('userId', this.userData?.id);

    this.userService.updateUserAvatar(formData).subscribe((result: any) => {
      this.snackBar.open("Uspjesno ste promijenili avatar!", undefined, {
        duration: 3000
      });
      this.changeImage = undefined;
      this.getAvatar();
    });
  }

}
