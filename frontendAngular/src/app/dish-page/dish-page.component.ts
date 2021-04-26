import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {Dish} from '../models/Dish';
import {OpenFoodFactsProduct} from '../models/OpenFoodFactsProduct';
import {BackendService} from '../backend.service';
import {ProductCart} from '../models/ProductCart';
import {User} from '../models/User';

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


  addProductsToCart(product: OpenFoodFactsProduct): void{

    const productList: OpenFoodFactsProduct[] = [];
    productList.push(product);

    const user: User = {
      username: localStorage.getItem('token')
    };

    const productCart: ProductCart = {
      openFoodFactsProducts: productList,
      user,
    };
    console.log('cart:' + product._id);
    this.backendService.addOpenFoodFactsProductsToCard(productCart).subscribe(
      o => {
        alert('Produit est ajouté dans le panier');
      }
    );

  }
}
