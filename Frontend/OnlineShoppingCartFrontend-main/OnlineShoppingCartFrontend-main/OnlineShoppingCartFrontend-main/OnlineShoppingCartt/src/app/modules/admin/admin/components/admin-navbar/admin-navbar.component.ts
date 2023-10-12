import { Component } from '@angular/core';
import { JwtServiceService } from 'src/app/Services/jwt-service.service';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css']
})
export class AdminNavbarComponent {


  constructor(private jwtService:JwtServiceService){}

  userName = this.jwtService.getUser();


  logout(){
    localStorage.clear();
    location.reload();
  }
}
