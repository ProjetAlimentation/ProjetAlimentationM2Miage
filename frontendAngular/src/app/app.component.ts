import { Component } from '@angular/core';
import {BackendService} from './backend.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  test: string;

  constructor(private http: BackendService) {
    this.http.test().subscribe(data => {
      this.test = data;
      console.log(this.test);
    });
  }
}
