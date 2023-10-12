import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { ProductService } from 'src/app/Services/ProductService';
import { Product } from 'src/app/model/productl';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent {
  products:Product = new Product();

  constructor(private productService:ProductService,private router:Router){}
  onSubmit(){
      console.log(this.products);
      this.saveProduct();
      alert('Product added successfully !');
  }
  saveProduct(){
    this.productService.addProduct(this.products)
    .subscribe(data=>{
      console.log(data);
      this.gotToProductList();
        })
  }
  gotToProductList(){
     this.router.navigate(['/product-list']);
  }

}






