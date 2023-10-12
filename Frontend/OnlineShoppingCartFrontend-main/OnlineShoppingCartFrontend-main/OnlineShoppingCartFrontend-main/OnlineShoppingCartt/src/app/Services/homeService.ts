import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { interval, map, switchMap } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { Cart } from '../model/cartItem';
import { JwtServiceService } from './jwt-service.service';
import { Address } from '../model/address';
import { OrderTable } from '../model/OrderTable';
import { Transaction } from '../model/Transacation';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class HomeService {


  getAllProductByName(searchTerm: string){
    return this.httpClient.get<Product[]>("http://localhost:3334/product/getAllProductbyName/"+searchTerm);
  }




  totalAddress:Address[]
  size:number;

  constructor(private httpClient:HttpClient,private localStorageService:LocalStorageService,private jwtService:JwtServiceService) { 
  }

  getAllProduct(){
    return this.httpClient.get<any>("http://localhost:3334/product/products")
    .pipe(map((res:any)=>{
        return res;
    }))
  }

  addProductToCart(productId:number){
    const cartId = this.jwtService.getCartId()
    return this.httpClient.post<Cart>("http://localhost:3333/cart/addingproducttocart/"+cartId+"/"+productId,
    {});
  }

  getCartProducts(){
    const cartId = this.jwtService.getCartId()
    return this.httpClient.get<Cart>("http://localhost:3333/cart/"+cartId)
  }

  decreaseProduct(productId:number){
    const cartId = this.jwtService.getCartId()
    return this.httpClient.put<Cart>("http://localhost:3333/cart/decreaseQuant/"+productId+"/"+cartId,{})
  }

  removeProductFromCart(productId:number){
    const cartId = this.jwtService.getCartId()
    return this.httpClient.put<Cart>("http://localhost:3333/cart/delete/item/"+productId+"/"+cartId,{})
  }

  clearCart(){
    const cartId = this.jwtService.getCartId()
    return this.httpClient.delete<string>("http://localhost:3333/cart/delete/"+cartId).subscribe(
      res => {
        alert("cart deleted")
        location.reload();
      },
      err => console.log(err)
    )
  }


  order(payamentMode:string){
    const cartId = this.jwtService.getCartId();
    return this.httpClient.post<Transaction>("http://localhost:8085/payment/doPayment/"+cartId+"/"+this.jwtService.getUser()+"/"+payamentMode,{})
  }


  addAddress(formData:any){
    formData.customerName = this.jwtService.getUser();
    const randomInt = this.getRandomInt(1, 1000);
    console.log(randomInt);
    formData.customerId = randomInt;
    console.log(this.jwtService.getUser());
    console.log(formData)
    return this.httpClient.post<Address>("http://localhost:8090/order/storeaddress",formData)
  }


  getRandomInt(min: number, max: number): number {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }


  getAllAddressByCustomerName(){
    return this.httpClient.get<Address[]>("http://localhost:8090/order/alladdress/"+this.jwtService.getUser());
  }



  placeOrder(res:any){
    return this.httpClient.post("http://localhost:8090/order/placeorder/"+this.jwtService.getCartId(),res)
  }


  getAllOrdersByCustomer(){
    return this.httpClient.get<OrderTable[]>("http://localhost:8090/order/getOrderByCustomerName/"+this.jwtService.getUser());
  }


  deleteAddressById(id:number){
    return this.httpClient.delete("http://localhost:8090/order/deleteAddress/"+id,{
      responseType:'text'
    });
  }
}
