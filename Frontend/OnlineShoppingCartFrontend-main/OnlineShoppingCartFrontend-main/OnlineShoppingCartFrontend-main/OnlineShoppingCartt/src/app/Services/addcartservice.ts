import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddCartService {

  constructor(private httpClient:HttpClient) { }

  getAllProduct(){
    return this.httpClient.get<any>("http://localhost:3334/product/products")
    .pipe(map((res:any)=>{
        return res;
    }))
  }
}