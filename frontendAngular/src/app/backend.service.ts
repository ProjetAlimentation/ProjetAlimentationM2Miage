import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

   backendUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {

  }

  test(): Observable<string> {
    return this.http.get(this.backendUrl + '/test', {headers: this.getHeaders(), responseType: 'text'});
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
