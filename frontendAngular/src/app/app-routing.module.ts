import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './login/auth.guard';
import {NavComponent} from './nav/nav.component';
import {WeeklyDietComponent} from './weekly-diet/weekly-diet.component';
import {HomePageComponent} from "./home-page/home-page.component";

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
      { path: 'weekly-diet', component: WeeklyDietComponent}
    ]},
  {
    path: 'home-page', component: HomePageComponent
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
