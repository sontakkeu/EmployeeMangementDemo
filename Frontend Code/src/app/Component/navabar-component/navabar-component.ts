import { Component } from '@angular/core';
import { LoginService } from '../../Service/login-service';

@Component({
  selector: 'app-navabar-component',
  standalone: false,
  templateUrl: './navabar-component.html',
  styleUrl: './navabar-component.css',
})
export class NavabarComponent {

  navbarTitle: string = 'Employee Management System';
  constructor(public loginService: LoginService) {}

  isAdmin(): boolean {
    return this.loginService.isAdmin();
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('role');
  }

  logout() {
    this.loginService.logout();
    window.location.href = '/login';
  }
}


