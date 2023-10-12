import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/model/Transacation';
import { AdminService } from '../admin-home/admin.service';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit{

  transactions:Transaction[]
  search:string = ""

  constructor(private adminService:AdminService){}
  ngOnInit(): void {
    this.getAllTransactions()
  }


  searchByTransactionId(){
    this.adminService.getAllTransactions().subscribe(
      res => {
        this.transactions = res
      },err => {
        console.log(err);
        
      }
    )
    return this.transactions.filter(transaction => transaction.transactionId.toString().includes(this.search));
  }



  




  getAllTransactions(){
    this.adminService.getAllTransactions().subscribe(
      res => {
        this.transactions = res
      },err => {
        console.log(err);
        
      }
    )
  }



}
