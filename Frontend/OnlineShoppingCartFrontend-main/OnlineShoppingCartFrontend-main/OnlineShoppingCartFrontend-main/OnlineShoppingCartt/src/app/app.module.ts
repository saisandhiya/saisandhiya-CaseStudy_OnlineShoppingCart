import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddProductComponent } from './Product/add-product/add-product.component';
import { UpdateProductComponent } from './Product/update-product/update-product.component';
import { ProductListComponent } from './Product/product-list/product-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { NavbarComponent } from './navbar/navbar.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { JwtInterceptor, JwtInterceptorProvider } from './Services/jwt.interceptor';






@NgModule({
  declarations: [
    AppComponent,
    AddProductComponent,
    UpdateProductComponent,
    ProductListComponent,  
    NavbarComponent,
    RegistrationComponent,
    LoginComponent,
       
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule


  ],
  providers: [JwtInterceptorProvider],

  bootstrap: [AppComponent]
})
export class AppModule { }
