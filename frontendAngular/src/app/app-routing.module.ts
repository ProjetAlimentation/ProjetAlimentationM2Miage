import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MainNavigationComponent} from './main-navigation/main-navigation.component';
import {AuthGuard} from './login/auth.guard';
import {NavComponent} from './nav/nav.component';

const routes: Routes = [
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'main', component: NavComponent, canActivate: [AuthGuard]
  },
  {
    path: '', redirectTo: '/main', pathMatch: 'full'
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
