import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {BackendService} from '../backend.service';
import {Dish} from '../models/Dish';
// @ts-ignore
import {ProfileInterface} from '../models/ProfileInterface';
import {FormControl, Validators} from '@angular/forms';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import {DishPageComponent} from '../dish-page/dish-page.component';


@Component({
  selector: 'app-weekly-diet',
  templateUrl: './weekly-diet.component.html',
  styleUrls: ['./weekly-diet.component.css']
})
export class WeeklyDietComponent implements OnInit {

  dishList: Dish[] = [];
  dishMap: Map<string, Dish[]> = new Map<string, Dish[]>();
  selectedProfile: string;

  profiles: ProfileInterface[] = [
    {value: 'VEGETALIEN', viewValue: 'Végétalien'},
    {value: 'VEGETARIAN', viewValue: 'Végétarien'},
    {value: 'GLUTEN_FREE', viewValue: 'Sans gluten'},
    {value: 'LACTOSE_FREE', viewValue: 'Sans lactose'}
  ];

  constructor(private backendService: BackendService, private dialog: MatDialog) {}

  ngOnInit(): void {
    const username = localStorage.getItem('token');

    this.backendService.getDishListAll(username, false).subscribe((dishMap) => {
      this.dishMap = dishMap;
    });
  }


  updateDietList(profile): void{
    const username = localStorage.getItem('token');

    this.backendService.getDishList(username, true, profile).subscribe((dishMap) => {
      this.dishMap = dishMap;
      console.log(dishMap);
    });
  }


  updateDishInDietList(dishKey, dishIndex, profile?): void{
    const username = localStorage.getItem('token');

    console.log("sds " + profile);
    this.backendService.getRegeneratedDish(username, dishKey, dishIndex, profile).subscribe( (dishMap) => {
      this.dishMap = dishMap;
      console.log(dishMap);
    });
  }


  openDialog(dish: Dish): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = dish;
    dialogConfig.width = '1200px';

    const dialogRef = this.dialog.open(DishPageComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });

  }

}
