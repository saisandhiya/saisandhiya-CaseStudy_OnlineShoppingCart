import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/Services/ProductService';
import { Product } from 'src/app/model/productl';


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})

  export class ProductListComponent implements OnInit{
    products:Product[]=[];
  
    constructor(private productService:ProductService,private router:Router){}
    
    ngOnInit(): void {
     this.getProducts();
    }
  
    getProducts(){
      this.productService.getAllProduct().subscribe(data=>{
        this.products=data;
        console.log(data);
      })
    }
  
    updateProduct(productId:number){
      this.router.navigate(['product-list/product-update/',productId])
    }
    deleteProduct(productId:number){
       this.productService.deleteProductById(productId)
      .subscribe(data=>{
        this.getProducts();
        alert('Product deleted Sucessfully')
      })
    }
  
    
  }
  
  


