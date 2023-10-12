import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './components/homepage/homepage.component';
import { HomeComponent } from './components/customer-products/home.component';
import { CartComponent } from './components/cart/cart.component';
import { CustomerProfileComponent } from './components/customer-profile/customer-profile.component';
import { CustomerAddressComponent } from './components/customer-address/customer-address.component';
import { OrdersComponent } from './components/orders/orders.component';

const routes: Routes = [
  {
    path:'homepage',component:HomepageComponent
  },
  {
    path:'products', component:HomeComponent
  },
  {
    path:'cart', component:CartComponent
  },
  {
    path:'profile', component:CustomerProfileComponent
  },
  {
    path:'orderpage', component:CustomerAddressComponent
  },
  {
    path:'allOrders', component:OrdersComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
