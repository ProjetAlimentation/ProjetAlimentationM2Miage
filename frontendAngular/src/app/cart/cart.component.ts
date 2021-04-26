import { Component, OnInit } from '@angular/core';
import {OpenFoodFactsProduct} from '../models/OpenFoodFactsProduct';
import {BackendService} from '../backend.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  openFoodFactsProducts: OpenFoodFactsProduct[];

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {
    const item = localStorage.getItem('token');
    this.backendService.getCartElements(item).subscribe(product => {
      this.openFoodFactsProducts = product;
    });
  }

  deleteProduct(productId: number): void {
    const item = localStorage.getItem('token');
    this.backendService.deleteProduct(item, productId).subscribe(product => {
      this.ngOnInit();
    });
  }
}
