import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";
import { Product } from "../model/productl";
@Injectable({

    providedIn: 'root'

})
export class ProductService{
    baseURL='http://localhost:3334/product';
    constructor(private httpClient: HttpClient) { }
    //getting all product details
    getAllProduct():Observable<Product[]>{
        return this.httpClient.get<Product[]>(`${this.baseURL}/products`)
    }
    //Adding Products
    addProduct(product:Product):Observable<Object>{
        return this.httpClient.post(`${this.baseURL}/add`,product);
    }
    

getProductById(id:number):Observable<Product>{
    return this.httpClient.get<Product>(`${this.baseURL}/getproduct/${id}`);
  }
  updateProducts(id:number,product:Product):Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/updateMapping/${id}`,product);
  }
  
  deleteProductById(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/deleteMapping/${id}`,{responseType:'text'});
  }

    
}