import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtServiceService } from '../Services/jwt-service.service';
import { LocalStorageService } from '../Services/local-storage.service';
import { Router } from '@angular/router';
import { JwtResponse } from './jwtResponse';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient,
    private jwtService:JwtServiceService,
    private localStorageService:LocalStorageService,
    private router:Router) { }



    login(data: any) {
      this.httpClient.post<JwtResponse>("http://localhost:9090/signIn",data)
        .subscribe(

          jwtToken =>{
            // console.log(jwtToken);
            this.jwtService.setToken(jwtToken.accessToken);
            this.localStorageService.set('jwt',jwtToken.accessToken);
            this.localStorageService.set('role',jwtToken.roles[0]);
            // this.jwtService.setCartId();
            if (this.jwtService.isCustomer()) {
              this.router.navigate(['customer/homepage']);
            }else if(this.jwtService.isAdmin()){
              this.router.navigate(['admin/homepage']);
            }
            // else{
            //   this.router.navigate(['']);
            // }
          },err => {
            alert("Invalid Credentials")
          }
        )
    }
}
