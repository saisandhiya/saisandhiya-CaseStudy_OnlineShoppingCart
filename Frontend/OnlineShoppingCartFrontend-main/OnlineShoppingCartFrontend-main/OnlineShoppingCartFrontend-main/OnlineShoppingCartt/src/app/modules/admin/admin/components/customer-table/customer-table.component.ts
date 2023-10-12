import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin-home/admin.service';
import { profile } from 'src/app/model/customerProfile';

@Component({
  selector: 'app-customer-table',
  templateUrl: './customer-table.component.html',
  styleUrls: ['./customer-table.component.css']
})
export class CustomerTableComponent implements OnInit{

  constructor(private adminService:AdminService){

  }

  users:profile[]

  ngOnInit(): void {
    this.getAllCustomers()
  }

  getAllCustomers(){
    this.adminService.getAllCustomers().subscribe(
      res => this.users = res,
      err => console.log(err)
    )
  }



}
