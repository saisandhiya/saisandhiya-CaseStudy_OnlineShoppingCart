import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';
import { HomeService } from 'src/app/Services/homeService';
import { OrderTable } from 'src/app/model/OrderTable';
import { CustomerAddressComponent } from '../customer-address/customer-address.component';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit{


  constructor(private homeService:HomeService,private router:Router){
  }



  goBack(){
    this.router.navigate(['customer/orderpage']);
  }






  ngOnInit(): void {
    this.getAllOrders()
  }


  orders:OrderTable[]


  getAllOrders(){
    this.homeService.getAllOrdersByCustomer().subscribe(
      res => this.orders = res,
      err => console.log(err)
    )
  }



}
