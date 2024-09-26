import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ProductService } from '../services/product.service';
import { objectToQueryString } from '../utils/StringUtils';
import { productValidator } from '../validators/product.validator';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {
  searchForm: FormGroup = new FormGroup({
    code: new FormControl('', [productValidator('code')]),
    name: new FormControl('', [productValidator('name')]),
    brand: new FormControl('', [productValidator('brand')]),
  });

  products: any = [];

  constructor(
    private productService: ProductService,
  ) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
      next: (v) => {
        console.log('vvvv', v);
        this.products = v;
      },
      error: (e) => console.error('exxxxx', e),
    });
  }

  onSubmit() {
    console.log(this.searchForm.value);
    const searchValue = { ...this.searchForm.value };

    Object.keys(searchValue).map((k) => {
      if (searchValue[k] == '') {
        delete searchValue[k];
      }
    });
    const querieString = objectToQueryString(searchValue);
    this.searchProducts(querieString);
  }
  
  searchProducts(querieString: string): void {
    console.log(querieString);
    this.productService.searchProducts(querieString).subscribe({
      next: (data) => {
        console.log(data);
        this.products = data;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }
  get code() {
    return this.searchForm.get('code');
  }
  get brand() {
    return this.searchForm.get('brand');
  }
  get name() {
    return this.searchForm.get('name');
  }
}
