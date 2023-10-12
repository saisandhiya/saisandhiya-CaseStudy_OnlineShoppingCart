import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtServiceService } from 'src/app/Services/jwt-service.service';
import { profile } from 'src/app/model/customerProfile';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient,private jwt:JwtServiceService) { }

  getProfile(){
    return this.http.get<profile>("http://localhost:9090/admin/getCustomerByUserName/"+this.jwt.getUser())
  }

  updateProfile(data:any){
    return this.http.put("http://localhost:9090/customer/access/updateCustomer",data)
  }
}
