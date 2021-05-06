import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Dish} from './models/Dish';
import {OpenFoodFactsProduct} from './models/OpenFoodFactsProduct';
import {ProductCart} from './models/ProductCart';
import {environment} from '../environments/environment';
import {Monitoring} from './models/Monitoring';
// @ts-ignore
import {ProfileInterface} from './models/ProfileInterface';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

   backendUrl = environment.API_URL;

  constructor(private http: HttpClient) {

  }

  addOpenFoodFactsProductsToCard(productCart: ProductCart): Observable<any> {
    // tslint:disable-next-line:max-line-length
    return this.http.post<OpenFoodFactsProduct>(this.backendUrl + '/addOpenFoodFactsProductsToCart', productCart, {headers: this.getHeaders()});
  }

  getCartElements(userId: string): Observable<OpenFoodFactsProduct[]> {
    // @ts-ignore
    return this.http.get<OpenFoodFactsProduct[]>(this.backendUrl + '/getCartElements?userId=' + userId, {headers: this.getHeaders()});
  }

  deleteProduct(userId: string, productId: number): Observable<any> {
    return this.http.delete(this.backendUrl + '/deleteProduct?userId=' + userId + '&productId=' + productId, {headers: this.getHeaders()});
  }

  getOpenFoodFactsProducts(productType: string[]): Observable<OpenFoodFactsProduct[]> {
    return this.http.post<OpenFoodFactsProduct[]>(this.backendUrl + '/getOpenFoodFactsProducts', productType, {headers: this.getHeaders()});
  }

  getDishList(username: string, refresh: boolean): Observable<Map<string, Dish[]>> {
    return this.http.get<Map<string, Dish[]>>(this.backendUrl + '/getWeeklyDietByProfile?username=' + username + '&refresh=' + refresh, {headers: this.getHeaders()});
  }

  getDishListAll(username: string, refresh: boolean): Observable<Map<string, Dish[]>> {
    // tslint:disable-next-line:max-line-length
    return this.http.get<Map<string, Dish[]>>(this.backendUrl + '/getWeeklyDietByProfile?username=' + username + '&refresh=' + refresh, {headers: this.getHeaders()});
  }

  /* utilisé pour la regénération d'un plat dans la dietlist */
  getRegeneratedDish(username: string, dishKey: string, dishIndex: number, profile?: string): Observable<Map<string, Dish[]>> {
    // tslint:disable-next-line:max-line-length
    const profileLink =  profile === undefined ? '' : '&profile=' + profile;
    return this.http.get<Map<string, Dish[]>>(this.backendUrl + '/getRegenerateDishByProfile?username=' + username + '&dishKey=' + dishKey + '&dishIndex=' + dishIndex + profileLink, {headers: this.getHeaders()});
  }

  test(): Observable<string> {
    return this.http.get(this.backendUrl + '/test', {headers: this.getHeaders(), responseType: 'text'});
  }

  checkLogin(login: string, password: string): Observable<boolean> {
    return this.http.get<boolean>(this.backendUrl + '/login?login=' + login + '&password=' + password, {headers: this.getHeaders()});
  }

  modifyLogin(login: string, password: string): Observable<void> {
    return this.http.post<void>(this.backendUrl + '/updateLogin?username=' + login + '&password=' + password, {headers: this.getHeaders()});
  }

  createMonitoring(weight: number, mental: number, diet: number ): Observable<void> {
    return this.http.post<void>(this.backendUrl + '/addMonitoring?weight=' + weight + '&mental=' + mental + '&diet=' + diet, {headers: this.getHeaders()});
  }

  getMonitoring(): Observable<Monitoring[]> {
    return this.http.get<Monitoring[]>(this.backendUrl + '/getMonitoring', {headers: this.getHeaders()});
  }


  getProfile(username: string): Observable<ProfileInterface> {
    return this.http.get<ProfileInterface>(this.backendUrl + '/getProfile?username=' + username, {headers: this.getHeaders()});
  }

  saveProfile(profile: ProfileInterface): Observable<void> {
    return this.http.post<void>(this.backendUrl + '/addProfile', profile, {headers: this.getHeaders()});
  }

  getHeaders(): HttpHeaders {
    const headers = new HttpHeaders();
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('Access-Control-Allow-Credentials', 'true');
    headers.append('Access-Control-Allow-Methods', 'GET, HEAD, OPTIONS, POST, PUT, DELETE');
    headers.append('Content-Type', 'application/json,application/x-www-form-urlencoded');
    headers.append('Access-Control-Request-Headers', 'Content-type,X-Requested-With,Origin,accept');
    headers.append('Access-Control-Allow-Headers', 'Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers');
    return headers;
  }
}
