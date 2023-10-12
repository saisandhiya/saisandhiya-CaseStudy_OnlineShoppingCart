import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomerService } from './customer.service';
import { profile } from 'src/app/model/customerProfile';

@Component({
  selector: 'app-customer-profile',
  templateUrl: './customer-profile.component.html',
  styleUrls: ['./customer-profile.component.css']
})
export class CustomerProfileComponent implements OnInit{



  profileForm:FormGroup;

  customerProfile:profile;

  constructor(private customerService:CustomerService){
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
    this.customerService.getProfile().subscribe(
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
    this.customerService.updateProfile(data).subscribe(
      res => {
        console.log(res);
        alert("Profile Updated")
      },
      err => console.log(err)
    )
  }


}
