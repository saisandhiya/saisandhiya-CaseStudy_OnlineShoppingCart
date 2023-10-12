import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
  export class CartService {
    private baseUrl = 'http://localhost:3333/cart'; // Replace with your backend API URL
    
    constructor(private http: HttpClient) {
      
    }




  }