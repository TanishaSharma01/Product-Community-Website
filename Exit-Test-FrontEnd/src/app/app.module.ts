import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterService } from './services/register.service';
import { StatsService } from './services/stats.service';
import { ProductSearchComponent } from './product-search/product-search.component';
import { LoggedinGuard } from './guards/login.guard';
import { ProductDetailComponent } from './product-detail/product-detail.component';
import { ReviewAddComponent } from './review-add/review-add.component';
import { ServerDownComponent } from './server-down/server-down.component';
import { FooterComponent } from './footer/footer.component';
import { HeadersInterceptor } from './services/headers.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    NavbarComponent,
    ProductSearchComponent,
    ProductDetailComponent,
    ReviewAddComponent,
    ServerDownComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule
  ],
  providers: [RegisterService, StatsService, LoggedinGuard,
  {
    provide:HTTP_INTERCEPTORS, useClass: HeadersInterceptor, multi:true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
