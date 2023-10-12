import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin-home/admin.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Product } from 'src/app/model/product';
import { OrderTable } from 'src/app/model/OrderTable';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit{

  product: Product;
  productForm:FormGroup
  productId:number


  selectedProduct: Product | null = null;

  constructor(private adminService:AdminService ){
    this.productForm = new FormGroup({
      productType: new FormControl(),
      productName: new FormControl(),
      category: new FormControl(),
      rating: new FormControl(),
      review: new FormControl(),
      image: new FormControl(),
      price: new FormControl(),
      description: new FormControl(),
      specification: new FormControl(),
    });
  }


  getProduct(id:number){
    this.adminService.getProductById(id).subscribe(
      res => {
        this.product = res
        this.initializeForm()
      }
    )
  }


  showProductDetails(product: Product): void {
    this.selectedProduct = product;
  }


  initializeForm() {
    this.productForm = new FormGroup({
      productType: new FormControl(this.product.productType, [Validators.required]),
      productName: new FormControl(this.product.productName,[Validators.required]),
      category: new FormControl(this.product.category, [Validators.required]),
      rating: new FormControl(this.product.rating, [Validators.required ,Validators.pattern('[1-5]')]),
      review: new FormControl(this.product.review, [Validators.required]),
      image: new FormControl(this.product.image,[Validators.required]),
      price: new FormControl(this.product.price, [Validators.required, Validators.pattern(/^\d+(\.\d{1,2})?$/)]),
      description: new FormControl(this.product.description, [Validators.required]),
      specification: new FormControl(this.product.specification, [Validators.required]),
    });
    this.productId = this.product.productId;
  }


  public productList: any;
  // public filteredProductList: any;
  search:string = ""


  ngOnInit(): void {
    this.adminService.getAllProduct().subscribe((res: any) => {
      this.productList = res;
      this.productList.forEach((a: any) => {
        Object.assign(a, { quantity: 1, total: a.price });
      });
    });
  }


  searchByProductName(): void {
    const searchTerm = this.search.toLowerCase();
    if (!searchTerm) {
      // Reset the product list if the search term is empty
      this.adminService.getAllProduct().subscribe((res: any) => {
        this.productList = res;
      });
    } else {
      // Filter the product list based on the search term
      this.productList = this.productList.filter(item =>
        item.productName.toLowerCase().includes(searchTerm)
      );
    }
  }


  updateForm(formData:any){
    this.adminService.updateProduct(formData,this.productId).subscribe(
      res => {
        alert("product updated")
        location.reload()
      },err => {
        console.log(err);
        
      }
    )
  }

  deleteProduct(id:number){
    this.adminService.deleteProductById(id).subscribe(
      res => {
        alert("product deleted")
        location.reload();
      },
      err => {
        console.log(err);
      }
    )
  }


  


}
