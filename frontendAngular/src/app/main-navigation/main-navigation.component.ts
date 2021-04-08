import { Component, OnInit } from '@angular/core';
import {BackendService} from '../backend.service';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {map, shareReplay} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-main-navigation',
  templateUrl: './main-navigation.component.html',
  styleUrls: ['./main-navigation.component.css']
})
export class MainNavigationComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,
              private backendService: BackendService, private router: Router) {}



  logout(): void {
    console.log('Logout');
    localStorage.setItem('isLoggedIn', 'false');
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }


}
