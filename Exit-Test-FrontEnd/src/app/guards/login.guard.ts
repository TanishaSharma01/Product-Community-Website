import {
  Router,
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
} from '@angular/router';

import { RegisterService } from '../services/register.service';
import { Injectable } from '@angular/core';

@Injectable()
export class LoggedinGuard implements CanActivate {
  constructor(private router: Router, private registerService: RegisterService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.registerService.isLoggedIn()) {
      return true; // Allow access to the route
    } else {
      this.router.navigate(['/login']); // Redirect to login page
      return false; // Prevent access to the route
    }
    return false;
  }
}

