import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { JwtServiceService } from '../Services/jwt-service.service';
import { LocalStorageService } from '../Services/local-storage.service';

export const adminGuard: CanActivateFn = (route, state) => {
  if(inject(JwtServiceService).getUser()){
    if(inject(JwtServiceService).isTokenExpired()){
      inject(Router).createUrlTree(['/']);
      inject(LocalStorageService).remove('jwt');
    }else{
      if(inject(JwtServiceService).isAdmin()){
        inject(Router).createUrlTree(['/homepage']);
        return true;
      }
    }
  }
  return inject(Router).createUrlTree(['/']);
};
