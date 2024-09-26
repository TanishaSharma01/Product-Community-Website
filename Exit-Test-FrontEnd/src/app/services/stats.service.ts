import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { handleError } from '../utils/ErrorHandling';
const baseUrl='http://localhost:8088/api/v1';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  constructor(private http:HttpClient) { }

  public getCountUser():Observable<any>
  {
    return this.http.get<any>(`${baseUrl}/users/count-users`).pipe(catchError(handleError));
  }
  public getCountProducts()
  {
    return this.http.get(`${baseUrl}/products/count-products`).pipe(catchError(handleError));
  }
  public getCountReviews()
  {
    return this.http.get(`${baseUrl}/reviews/count-reviews`).pipe(catchError(handleError));
  }
}
