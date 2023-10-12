import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { CartService } from 'src/app/Services/cartService';
import { HomeService } from 'src/app/Services/homeService';
import { Cart } from 'src/app/model/cartItem';
import { Product } from 'src/app/model/product';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  productList: Product[] = [];
  search: string = "";
  cart: Cart;

  
  selectedProduct: Product | null = null;

  // Subject for debouncing the search input
  // private searchSubject = new Subject<string>();

  constructor(private homeService: HomeService, private cartService: CartService, private router: Router) { }

  ngOnInit(): void {

    this.homeService.getAllProduct().subscribe((res: any) => {
      this.productList = res;
      this.productList.forEach((a: any) => {
        Object.assign(a, { quantity: 1, total: a.price });
      });
    });
    
  }

  showProductDetails(product: Product): void {
    this.selectedProduct = product;
  }

  searchByProductName(): void {
    const searchTerm = this.search.toLowerCase();
    if (!searchTerm) {
      // Reset the product list if the search term is empty
      this.homeService.getAllProduct().subscribe((res: any) => {
        this.productList = res;
      });
    } else {
      // Filter the product list based on the search term
      this.productList = this.productList.filter(item =>
        item.productName.toLowerCase().includes(searchTerm)
      );
    }
  }

  addProduct(productId: number): void {
    this.homeService.addProductToCart(productId).subscribe(
      res => {
        this.cart = res;
        this.router.navigate(['/customer/cart']);
      },
      err => console.log(err)
    )
  }
}
