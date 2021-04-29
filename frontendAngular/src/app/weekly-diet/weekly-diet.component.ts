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

    this.backendService.getRegeneratedDish(username, dishKey, dishIndex, profile).subscribe( dishMap => {
      this.dishMap = dishMap;
      console.log(dishMap);
    });
  }

  /* key et isFirst à retirer et à déplacer dans une autre méthode + reporter les modifs sur l'html*/
  openDialog(dish: Dish, dishKey: string, dishIndex: number): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = dish;
    dialogConfig.width = '1200px';

    const dialogRef = this.dialog.open(DishPageComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
    // cette deuxième partie est à reporter dans une autre méthode qui sera appeler par un bouton sur un dish
    // le back est OK mais est à optimiser
    // let profileValue;

    // tslint:disable-next-line:forin
    /*for (const index in this.profiles) {
      if (this.profiles[index].viewValue === dish.profile.toString()) {
          profileValue = this.profiles[index].value;
          break;
      }
    } */

    /*if (profileValue === undefined){
      this.updateDishInDietList(dishKey, dishIndex);
    }else{
      this.updateDishInDietList(dishKey, dishIndex, profileValue);
    }

    console.log(`key: ${dishKey}`);
    console.log(`valeur index: ${dishIndex}`); */
  }

}
