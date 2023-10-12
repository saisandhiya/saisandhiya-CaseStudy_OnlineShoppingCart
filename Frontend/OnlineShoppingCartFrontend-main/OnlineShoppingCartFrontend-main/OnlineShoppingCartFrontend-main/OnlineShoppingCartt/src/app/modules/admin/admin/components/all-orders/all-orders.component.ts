import { Component } from '@angular/core';
import { AdminService } from '../admin-home/admin.service';
import { OrderTable } from 'src/app/model/OrderTable';
import { Router } from '@angular/router';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-all-orders',
  templateUrl: './all-orders.component.html',
  styleUrls: ['./all-orders.component.css']
})
export class AllOrdersComponent {



  constructor(private adminService:AdminService,private router:Router){}

  orders:OrderTable[]
  fileName= 'orderReport.xlsx';


  ngOnInit(): void {
    this.getAllOrders()
  }

  getAllOrders(){
    this.adminService.getAllOrders().subscribe(
      res => this.orders = res,
      err => console.log(err)
    )
  }

  goToPayments(){
    this.router.navigate(['admin/payments']);
  }

  exportexcel(): void
  {
    /* pass here the table id */
    let element = document.getElementById('excel-table');
    const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(element);

    /* generate workbook and add the worksheet */
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

    /* save to file */
    XLSX.writeFile(wb, this.fileName);

  }
}
