import { Component } from '@angular/core';
import { EmployeeService } from '../../Service/employee-service';
import { LoginService } from '../../Service/login-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-component',
  standalone: false,
  templateUrl: './admin-component.html',
  styleUrl: './admin-component.css',
})
export class AdminComponent {
  constructor(
    private loginService: LoginService,
    private router: Router
  ) {}

  logout() {
    this.loginService.logout();
    this.router.navigate(['/login']);
  }
  
}