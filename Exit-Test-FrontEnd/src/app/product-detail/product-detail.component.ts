import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productData: any = null;
  productReviews: any = [];
  constructor(
    private productService: ProductService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    const productId =this.activatedRoute.snapshot.paramMap.get('productId') || '';
    if (productId == '') {
      return;
    }
    this.productService.getProductById(productId).subscribe({
      next: (data) => {
        this.productData = data;
        this.productReviews = this.productData.reviews;
        console.log(this.productData);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  reviewClicked(review: Object, reviewId: string) {
    // console.log(review);
    this.router.navigate(['/reviews/detail', reviewId], {
      state: {
        reviewData: review,
        productData: this.productData,
      },
    });
  }

  addReviewBtnClicked() {
    this.router.navigate(['/reviews/add'], {
      state: {
        productData: this.productData,
      },
    });
  }
}

