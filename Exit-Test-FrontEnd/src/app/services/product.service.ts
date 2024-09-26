import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  constructor(private http: HttpClient) {}
  BASE_URL = 'http://localhost:8088/api/v1';

  getProducts(): Observable<any> {
    const url = this.BASE_URL + '/products/search';
    return this.http.get(url).pipe(
      map((response: any) => {
        return response.data;
      })
    );
  }
  getProductById(id: String): Observable<any> {
    const url = this.BASE_URL + '/products/' + id;
    return this.http.get(url).pipe(
      map((response: any) => {
        return response.data;
      })
    );
  }
  searchProducts(queryString: string): Observable<any> {
    const url = this.BASE_URL + '/products/search?' + queryString;
    return this.http.get(url).pipe(
      map((response: any) => {
        return response.data;
      })
    );
  }
  // addProduct(product: Object): Observable<any> {
  //   const url = this.BASE_URL + '/products/add';
  //   return this.http.post(url, product);
  // }
}
