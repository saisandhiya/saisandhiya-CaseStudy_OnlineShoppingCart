import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { profile } from 'src/app/model/customerProfile';
import { AdminService } from '../admin-home/admin.service';

@Component({
  selector: 'app-admin-profile',
  templateUrl: './admin-profile.component.html',
  styleUrls: ['./admin-profile.component.css']
})
export class AdminProfileComponent {


  profileForm:FormGroup;

  customerProfile:profile;



  constructor(private adminService:AdminService){
    this.profileForm = new FormGroup({
      userName: new FormControl(),
      firstName: new FormControl(),
      lastName: new FormControl(),
      mobileNumber: new FormControl(),
      email: new FormControl(),
    });
  }


  ngOnInit(): void {
    this.getCustomerProfile()
  }

  getCustomerProfile(){
    this.adminService.getProfile().subscribe(
      res => {
        this.customerProfile = res
        console.log(this.customerProfile);
        this.initializeForm()
      }
    )
  }


  initializeForm() {

    this.profileForm = new FormGroup({
      userName: new FormControl(this.customerProfile.userName, [Validators.required]),
      firstName: new FormControl(this.customerProfile.firstName,[Validators.required,Validators.pattern("^[A-Za-z]+$")]),
      lastName: new FormControl(this.customerProfile.lastName, [Validators.required,Validators.pattern("^[A-Za-z]+$")]),
      mobileNumber: new FormControl(this.customerProfile.mobileNumber,[Validators.required, Validators.pattern("^(\\+91|\\+91\\-|)?[6789]\\d{9}$")]),
      email: new FormControl(this.customerProfile.email, [Validators.required,Validators.pattern("[^@\\s]+@[^@\\s]+\\.[^@\\s]+"), Validators.email]),
    });
    this.profileForm.get('userName').disable();
  }


  updateCustomerProfile(data:any){
    data.userName = this.customerProfile.userName
    this.adminService.updateProfile(data).subscribe(
      res => {
        console.log(res);
        alert("Profile Updated")
      },
      err => console.log(err)
    )
  }

}
