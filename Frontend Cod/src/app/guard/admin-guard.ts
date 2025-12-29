import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';

export const adminGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const role = localStorage.getItem('role');

  if (role === 'ADMIN') {
    return true;
  }

  alert('Access Denied! Admin only.');
  router.navigate(['/login']);
  return false; 
};
