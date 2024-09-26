import { Component } from '@angular/core';
import { StatsService } from '../services/stats.service';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  loggedIn=false;
  // connection_refused='';
  constructor(public statsService: StatsService, public registerService: RegisterService, private router: Router) { 
    this.loggedIn = this.registerService.isLoggedIn();
  }
  noOfUser:any;
  noOfReviews:any;
  noOfProducts:any;
  ngOnInit(): void {
    this.statsService.getCountUser()
    .subscribe(
      data=>
      {
        this.noOfUser=data;
        // console.log(data);
      },
      error=>
      {
        // this.connection_refused='Server Down, Please try after sometime';
        this.router.navigate(['/serverdown']).then(() => {
          window.location.reload();
        });
        console.log(error);
      }
    )
  this.statsService.getCountProducts()
    .subscribe(
      data=>
      {
        this.noOfProducts=data;
        // console.log(data);
      },
      error=>
      {
        console.log(error);
      }
    )
    this.statsService.getCountReviews()
    .subscribe(
      data=>
      {
        this.noOfReviews=data;
        // console.log(data);
      },
      error=>
      {
        console.log(error);
      }
    )
  }

}
