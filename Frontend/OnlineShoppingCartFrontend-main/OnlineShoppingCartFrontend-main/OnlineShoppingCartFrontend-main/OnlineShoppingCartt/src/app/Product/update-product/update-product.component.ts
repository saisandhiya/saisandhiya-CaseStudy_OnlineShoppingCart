import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/Services/ProductService';
import { Product } from 'src/app/model/productl';


@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent {
  constructor(private productService:ProductService,private route:ActivatedRoute,
    private router:Router){}

  products:Product=new Product();
  productId!:number ;

  ngOnInit(): void {
    this.productId=this.route.snapshot.params['productId'];
  
    this.productService.getProductById(this.productId)
    .subscribe(data=>{
      this.products=data;
      console.log(this.products)
    })
  }
  
  onSubmit(){
    this.productService.updateProducts(this.productId,this.products)
    .subscribe(data=>{
      console.log(data);
      alert('Product Updated Successfully !')
      this.goToProductList();
    })
  }
  goToProductList(){
    this.router.navigate(['/product-list'])
  }
}



