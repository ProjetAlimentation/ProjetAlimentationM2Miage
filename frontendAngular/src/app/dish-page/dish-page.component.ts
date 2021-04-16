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
  openFoodFactsProducts: OpenFoodFactsProduct[];
  listNutrition: string[] = ['Protéines', 'Lipides', 'Acides gras saturés', 'Glucides', 'Sucre', 'Sel', 'Fibres', 'Énergie'];

  constructor(@Inject(MAT_DIALOG_DATA) data, private backendService: BackendService) {
    this.dish = data;


  }

  ngOnInit(): void {
      this.backendService.getOpenFoodFactsProducts(this.dish.ingredients).subscribe(product => {
        console.log('pr' + product);
        this.openFoodFactsProducts = product;
      });
  }


  addProductsToCart(): void{

  }
}
