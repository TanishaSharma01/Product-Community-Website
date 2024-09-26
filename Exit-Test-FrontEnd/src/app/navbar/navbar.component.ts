import { Component } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';
import { User } from '../class/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  loggedIn = false;
  user:any = null;
  constructor(public registerService: RegisterService, private router: Router) {
    this.loggedIn = this.registerService.isLoggedIn();
    this.user = this.registerService.getLoggedUser();
   }
  
   logout() {
    this.registerService.logoutUser().subscribe({
      next: (res) => {
        console.log(res);
        localStorage.clear();
        this.router.navigate(['/']).then(() => {
          window.location.reload();
        });
      },
      error: (err) => console.log(err),
    });
  }

}
