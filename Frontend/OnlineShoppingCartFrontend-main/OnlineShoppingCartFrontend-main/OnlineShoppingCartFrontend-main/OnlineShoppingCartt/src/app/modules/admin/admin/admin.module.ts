import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AllProductsComponent } from './components/all-products/all-products.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { AllOrdersComponent } from './components/all-orders/all-orders.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { CustomerTableComponent } from './components/customer-table/customer-table.component';


@NgModule({
  declarations: [
    AdminNavbarComponent,
    AdminHomeComponent,
    AllProductsComponent,
    AdminProfileComponent,
    AllOrdersComponent,
    PaymentsComponent,
    CustomerTableComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminModule { }
