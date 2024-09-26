import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { handleError } from '../utils/ErrorHandling';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  constructor(private http: HttpClient) {}
  BASE_URL = 'http://localhost:8088/api/v1';

  getReviews(): Observable<any> {
    let url = this.BASE_URL + '/reviews';
    return this.http.get(url).pipe(
      map((response: any) => {
        return response.data;
      })
    );
  }

  saveReview(reviewValue: Object, productCode: String): Observable<any> {
    const url = this.BASE_URL + '/reviews/add/' + productCode;
    return this.http.put(url, reviewValue).pipe(catchError(handleError));
  }
}

