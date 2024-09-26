import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { ProductSearchComponent } from './product-search/product-search.component';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { LoggedinGuard } from './guards/login.guard';
import { ReviewAddComponent } from './review-add/review-add.component';
import { ServerDownComponent } from './server-down/server-down.component';

const routes: Routes = [
  {
    path:'login',
    component:LoginComponent
  },
  {
    path:'',
    component:HomeComponent
  },
  {
    path:'register',
    component:RegisterComponent
  },
  {
    path: 'products/search',
    component: ProductSearchComponent,
    canActivate: [LoggedinGuard],
  },
  {
    path: 'product/detail/:productId',
    component: ProductDetailComponent,
    canActivate: [LoggedinGuard],
  },
  {
    path: 'reviews/add',
    component: ReviewAddComponent,
    canActivate: [LoggedinGuard],
  },
  {
    path:'serverdown',
    component: ServerDownComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
