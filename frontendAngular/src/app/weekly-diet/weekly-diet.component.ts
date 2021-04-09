import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {BackendService} from '../backend.service';
import {Dish} from '../models/Dish';

@Component({
  selector: 'app-weekly-diet',
  templateUrl: './weekly-diet.component.html',
  styleUrls: ['./weekly-diet.component.css']
})
export class WeeklyDietComponent implements OnInit {

  dishList: Dish[] = [];
  dishMap: Map<string, Dish[]> = new Map<string, Dish[]>();

  constructor(private backendService: BackendService) {}

  ngOnInit(): void {
    this.backendService.getDishList().subscribe(dishMap => {
      this.dishMap = dishMap;
  });

    console.log(this.dishMap);

  }



}
