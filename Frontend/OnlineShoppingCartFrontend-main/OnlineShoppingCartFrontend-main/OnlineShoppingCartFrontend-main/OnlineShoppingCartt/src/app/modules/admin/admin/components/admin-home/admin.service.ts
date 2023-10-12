import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';
import { JwtServiceService } from 'src/app/Services/jwt-service.service';
import { OrderTable } from 'src/app/model/OrderTable';
import { Transaction } from 'src/app/model/Transacation';
import { profile } from 'src/app/model/customerProfile';
import { Product } from 'src/app/model/product';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient,private jwt:JwtServiceService) { }





  addProduct(formData:any){
    return this.http.post("http://localhost:3334/product/add",formData)
  }

  getAllProduct(){
    return this.http.get<any>("http://localhost:3334/product/products")
    .pipe(map((res:any)=>{
        return res;
    }))
  }


  getProductById(id:number){
    return this.http.get<Product>("http://localhost:3334/product/getproduct/"+id)
  }


  updateProduct(data:any,id:number){
    return this.http.put("http://localhost:3334/product/updateMapping/"+id ,data);
  }

  deleteProductById(id:number){
    return this.http.delete("http://localhost:3334/product/deleteMapping/"+id,{
      responseType:'text'
    })
  }


  getProfile(){
    return this.http.get<profile>("http://localhost:9090/admin/getCustomerByUserName/"+this.jwt.getUser())
  }

  updateProfile(data:any){
    return this.http.put("http://localhost:9090/customer/access/updateCustomer",data)
  }

  getAllOrders(){
    return this.http.get<OrderTable[]>("http://localhost:8090/order/allorders");
  }


  getAllTransactions(){
    return this.http.get<Transaction[]>("http://localhost:8085/payment/getAllPayments")
  }

  getAllCustomers(){
    return this.http.get<profile[]>("http://localhost:9090/admin/getCustomers/")
  }

}
