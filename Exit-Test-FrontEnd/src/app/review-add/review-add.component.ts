import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormControl,
  Validators,
} from '@angular/forms';
import { ReviewService } from '../services/review.service';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from '../services/register.service';
import { reviewValidator } from '../validators/review.validator';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-review-add',
  templateUrl: './review-add.component.html',
  styleUrls: ['./review-add.component.css'],
})
export class ReviewAddComponent implements OnInit {
  reviewForm: FormGroup = new FormGroup({
    title: new FormControl('', [reviewValidator('title')]),
    description: new FormControl('', [reviewValidator('description')]),
    rating: new FormControl(0, [reviewValidator('rating')]),
    createdBy: new FormControl('', [Validators.required]),
  });
  isSubmitBtnClicked = false;
  productDetail: any = null;
  starCounter = 0;
  constructor(
    private router: Router,
    private reviewService: ReviewService,
    private registerService: RegisterService
  ) {
    const state = this.router.getCurrentNavigation()?.extras.state;
    if (state?.['productData']) {
      this.productDetail = state?.['productData'];
      // console.log(this.productDetail);
    }
    const currentUser = this.registerService.getLoggedUser();
    this.reviewForm.get('createdBy')?.setValue(currentUser.firstName);
  }
  ngOnInit(): void {}
  onSubmit() {
    this.isSubmitBtnClicked = true;
    // const reviewValue = this.reviewForm.value;
    // console.log(this.rating);
    if (this.reviewForm.valid) {
      this.postReview();
    }
  }

  updateRating(star: number) {
    this.starCounter = star;
    this.reviewForm.get('rating')?.setValue(star);
  }

  postReview() {
    const formValue = this.reviewForm.value;
    this.reviewService
      .saveReview(formValue, this.productDetail.code)
      .subscribe({
        next: (data) => {
          console.log(data);
          this.resetForm();
          this.router.navigate([`/product/detail/${this.productDetail.code}`]);
        },
        error: (error) => {
          console.log(error);
        },
      });
  }

  resetForm() {
    this.reviewForm.reset();
    this.isSubmitBtnClicked = false;
  }
  backBtnClicked() {
    window.history.back();
  }

  get title() {
    return this.reviewForm.get('title');
  }
  get description() {
    return this.reviewForm.get('description');
  }
  get rating() {
    return this.reviewForm.get('rating');
  }
  get createdBy() {
    return this.reviewForm.get('createdBy');
  }
}
