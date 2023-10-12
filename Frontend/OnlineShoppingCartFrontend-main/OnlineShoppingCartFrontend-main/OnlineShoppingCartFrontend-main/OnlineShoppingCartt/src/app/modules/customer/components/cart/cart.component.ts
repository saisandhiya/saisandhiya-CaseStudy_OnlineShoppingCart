import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HomeService } from 'src/app/Services/homeService';
import { Cart, CartItem } from 'src/app/model/cartItem';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit{



  cartProductsList:Cart
  cartItems:CartItem[]

  totalPrice:number
  

  totalProducts:number=0;
 

  constructor(public homeService:HomeService, private router:Router){

  }


  getAllProducts(){
    this.homeService.getCartProducts().subscribe(
      res => {
        this.cartProductsList = res
        this.cartItems = res.items
        if(this.cartItems.length > 0){
          this.checkOutButton = false;
        }
        this.totalPrice = this.cartProductsList.totalPrice
        this.totalProducts = this.cartProductsList.items.length
        // console.log(this.cartProductsList);
      },
      err => console.log(err)
    )
  }

  checkOutButton = true;
 

  ngOnInit(): void {
   this.getAllProducts()
  }

  decreaseProduct(productId:number){
    this.homeService.decreaseProduct(productId).subscribe(
      res => {
        this.cartProductsList = res
        location.reload();
      },
      err => {
        console.log(err);
        
      }
    )
  }

  increaseProduct(productId:number){
    this.homeService.addProductToCart(productId).subscribe(
      res => {
        this.cartProductsList = res
        location.reload();
      },
      err => {
        console.log(err);
        
      }
    )
  }

  removeProduct(productId:number){
    this.homeService.removeProductFromCart(productId).subscribe(
      res => {
        this.cartProductsList = res
        location.reload();
      },
      err => {
        console.log(err)
      }
    )
  }


  checkOut(){
   this.router.navigate(['customer/orderpage'])
  }




}
