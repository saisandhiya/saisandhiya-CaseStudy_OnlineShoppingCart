import { Component } from '@angular/core';
import { JwtServiceService } from 'src/app/Services/jwt-service.service';

@Component({
  selector: 'app-customer-navbar',
  templateUrl: './customer-navbar.component.html',
  styleUrls: ['./customer-navbar.component.css']
})
export class CustomerNavbarComponent {

  constructor(private jwtService:JwtServiceService){

  }

  userName = this.jwtService.getUser();

  logout(){
    localStorage.clear();
    location.reload();
  }

}
