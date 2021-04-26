import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './login/auth.guard';
import {NavComponent} from './nav/nav.component';
import {WeeklyDietComponent} from './weekly-diet/weekly-diet.component';
import {CartComponent} from './cart/cart.component';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: '', redirectTo: '/dashboard', pathMatch: 'full'
  },
  {
    path: 'dashboard', component: NavComponent, canActivate: [AuthGuard],
    children: [
      { path: 'weekly-diet', component: WeeklyDietComponent},
      { path: 'cart', component: CartComponent}
    ]}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
