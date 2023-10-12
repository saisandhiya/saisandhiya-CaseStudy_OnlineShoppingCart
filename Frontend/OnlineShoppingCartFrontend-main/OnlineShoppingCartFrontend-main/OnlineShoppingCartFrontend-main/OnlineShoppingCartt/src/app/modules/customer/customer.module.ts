import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { HomeComponent } from 'src/app/modules/customer/components/customer-products/home.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { CustomerNavbarComponent } from './components/customer-navbar/customer-navbar.component';
import { CartComponent } from './components/cart/cart.component';
import { CustomerProfileComponent } from './components/customer-profile/customer-profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomerAddressComponent } from './components/customer-address/customer-address.component';
import { OrdersComponent } from './components/orders/orders.component';


@NgModule({
  declarations: [
    HomeComponent,
    HomepageComponent,
    CustomerNavbarComponent,
    CartComponent,
    CustomerProfileComponent,
    CustomerAddressComponent,
    OrdersComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers:[CartComponent,CustomerAddressComponent]
})
export class CustomerModule { }
