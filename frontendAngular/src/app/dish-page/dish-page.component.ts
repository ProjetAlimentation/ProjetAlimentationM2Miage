import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Dish} from '../models/Dish';
import {OpenFoodFactsProduct} from '../models/OpenFoodFactsProduct';
import {BackendService} from '../backend.service';

@Component({
  selector: 'app-dish-page',
  templateUrl: './dish-page.component.html',
  styleUrls: ['./dish-page.component.css']
})
export class DishPageComponent implements OnInit {

  dish: Dish;
  openFoodFactsProducts: OpenFoodFactsProduct[] = [];

  constructor(@Inject(MAT_DIALOG_DATA) data, private backendService: BackendService) {
    this.dish = data;


  }

  ngOnInit(): void {
    this.dish.ingredients.forEach(o => {
      this.backendService.getOpenFoodFactsProducts(o).subscribe(product => {
        console.log('pr' + product[0]);
        if (product.length !== 0){
          this.openFoodFactsProducts.push(product[0]);
        }
      });
    });
    console.log(this.openFoodFactsProducts.length);

  }

}
