import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AllProductsComponent } from './components/all-products/all-products.component';
import { AdminProfileComponent } from './components/admin-profile/admin-profile.component';
import { AllOrdersComponent } from './components/all-orders/all-orders.component';
import { PaymentsComponent } from './components/payments/payments.component';
import { CustomerTableComponent } from './components/customer-table/customer-table.component';

const routes: Routes = [
  {
    path:'homepage',component:AdminHomeComponent
  },
  {
    path:'products', component:AllProductsComponent
  },
  {
    path:'profile', component:AdminProfileComponent
  },
  {
    path:'orders', component:AllOrdersComponent
  },
  {
    path:'payments', component:PaymentsComponent
  },
  {
    path:'customers', component:CustomerTableComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
