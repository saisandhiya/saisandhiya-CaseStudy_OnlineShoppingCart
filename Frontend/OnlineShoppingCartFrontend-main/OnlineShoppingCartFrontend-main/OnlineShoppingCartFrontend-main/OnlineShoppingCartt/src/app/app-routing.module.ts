import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { adminGuard } from './AuthService/admin.guard';
import { customerGuard } from './AuthService/customer.guard';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './modules/customer/components/customer-products/home.component';
import { RegistrationComponent } from './registration/registration.component';




const routes: Routes = [
  {path:'',component: LoginComponent},
  { path: 'customer-products/home', component: HomeComponent },
  {
    path:'registration',component:RegistrationComponent
  },

  {
    path:'customer',
    canMatch:[customerGuard],
    loadChildren:() => import('./modules/customer/customer.module').then((m) => m.CustomerModule)
  },
  {
    path:'admin',
    canMatch:[adminGuard],
    loadChildren:() => import('./modules/admin/admin/admin.module').then((m) => m.AdminModule)
  }





];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
